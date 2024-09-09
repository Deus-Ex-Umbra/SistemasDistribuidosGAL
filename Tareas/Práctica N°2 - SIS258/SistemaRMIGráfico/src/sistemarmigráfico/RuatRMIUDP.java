package sistemarmigráfico;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RuatRMIUDP extends UnicastRemoteObject implements RuatInterface {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/DBDEUDAS";
    private static final String USER = "root";
    private static final String PASS = "password"; // Cambiar por la contraseña real
    private static final String ALCALDIA_IP = "localhost"; // IP del servidor UDP de la Alcaldía
    private static final int ALCALDIA_PORT = 9876; // Puerto del servidor UDP de la Alcaldía

    public RuatRMIUDP() throws RemoteException {
        super();
    }

    // Método para consultar deudas (RMI)
    @Override
    public String consultarDeudas(String ci) throws RemoteException {
        StringBuilder response = new StringBuilder();

        // Primero consulta a la alcaldía para ver si el CI tiene observaciones
        if (consultarAlcaldia(ci)) {
            return "El CI tiene observaciones, no se puede consultar deudas.";
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String query = "SELECT Anio, Impuesto, Monto FROM Deuda WHERE CI = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, ci);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int year = rs.getInt("Anio");
                String taxType = rs.getString("Impuesto");
                double amount = rs.getDouble("Monto");
                response.append("Año: ").append(year)
                        .append(", Impuesto: ").append(taxType)
                        .append(", Monto: ").append(amount).append("\n");
            }

            if (response.length() == 0) {
                return "No hay deudas.";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error consultando deudas.";
        }
        return response.toString();
    }

    // Método para procesar pagos (RMI)
    @Override
    public String procesarPago(String ci, int year, String taxType, double amount) throws RemoteException {
        if (consultarAlcaldia(ci)) {
            return "El CI tiene observaciones, no se puede procesar el pago.";
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Verificar si la deuda existe
            String query = "SELECT Monto FROM Deuda WHERE CI = ? AND Anio = ? AND Impuesto = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, ci);
            stmt.setInt(2, year);
            stmt.setString(3, taxType);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double currentAmount = rs.getDouble("Monto");
                if (amount >= currentAmount) {
                    String deleteQuery = "DELETE FROM Deuda WHERE CI = ? AND Anio = ? AND Impuesto = ?";
                    PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                    deleteStmt.setString(1, ci);
                    deleteStmt.setInt(2, year);
                    deleteStmt.setString(3, taxType);
                    deleteStmt.executeUpdate();
                    return "Deuda pagada exitosamente.";
                } else {
                    return "Monto insuficiente para pagar la deuda.";
                }
            } else {
                return "Deuda no encontrada.";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error procesando pago.";
        }
    }

    // Método para consultar a la Alcaldía usando UDP
    private boolean consultarAlcaldia(String ci) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress ip = InetAddress.getByName(ALCALDIA_IP);
            byte[] sendBuffer = ("consulta:" + ci).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ip, ALCALDIA_PORT);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

            return response.equals("true"); // Si es "true", el CI tiene observaciones
        } catch (Exception e) {
            e.printStackTrace();
            return true; // En caso de error, se asume que hay observaciones
        }
    }
    
    public static void main(String[] args) {
        try {
            RuatRMIUDP ruat = new RuatRMIUDP();
            Registry registry = LocateRegistry.createRegistry(1099); // Puerto RMI
            registry.bind("RuatService", ruat);
            System.out.println("Servidor RUAT listo para recibir peticiones...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
