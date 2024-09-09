package sistemarmigráfico;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BancoTCPRMI {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Banco escuchando en el puerto " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado.");
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException ex) {
            System.out.println("Error en el servidor de banco: " + ex.getMessage());
        }
    }
}
