package sistemarmigráfico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader fromClient;
    private PrintStream toClient;
    private RuatInterface ruatService;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            // Conectar con el servidor RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ruatService = (RuatInterface) registry.lookup("RuatService");
        } catch (Exception e) {
            System.out.println("Error conectando al servicio RMI: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            toClient = new PrintStream(clientSocket.getOutputStream());

            String request;
            while ((request = fromClient.readLine()) != null) {
                if (request.startsWith("Deuda:")) {
                    String ci = request.split(":")[1];
                    
                    // Usar el servicio RMI para consultar deudas
                    String response = ruatService.consultarDeudas(ci);
                    toClient.println(response);

                } else if (request.startsWith("Pagar:")) {
                    String[] details = request.split(":")[1].split(",");
                    String ci = details[0];
                    int year = Integer.parseInt(details[1]);
                    String taxType = details[2];
                    double amount = Double.parseDouble(details[3]);

                    // Usar el servicio RMI para procesar el pago
                    String response = ruatService.procesarPago(ci, year, taxType, amount);
                    toClient.println(response);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error manejando la conexión: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error comunicándose con el servicio RMI: " + ex.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                System.out.println("Error cerrando la conexión: " + ex.getMessage());
            }
        }
    }
}
