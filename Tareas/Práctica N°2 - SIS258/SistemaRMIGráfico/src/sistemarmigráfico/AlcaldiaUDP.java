package sistemarmigráfico;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AlcaldiaUDP {
    public static void main(String[] args) {
        int port = 6000;  // Puerto para la alcaldía
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Alcaldía UDP esperando consultas...");

            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String receivedMessage = new String(request.getData(), 0, request.getLength());
                System.out.println("Consulta recibida: " + receivedMessage);

                // Si la consulta tiene el formato "consulta:ci"
                if (receivedMessage.startsWith("consulta:")) {
                    String ci = receivedMessage.split(":")[1].trim();
                    boolean hasObservations = ci.equals("1234567"); // El CI 1234567 tiene observaciones

                    String response = hasObservations ? "false" : "true";  // false = observaciones, true = sin observaciones
                    byte[] responseData = response.getBytes();

                    InetAddress clientAddress = request.getAddress();
                    int clientPort = request.getPort();
                    DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                    socket.send(responsePacket);

                    System.out.println("Respuesta enviada: " + response);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor UDP de Alcaldía: " + e.getMessage());
        }
    }
}
