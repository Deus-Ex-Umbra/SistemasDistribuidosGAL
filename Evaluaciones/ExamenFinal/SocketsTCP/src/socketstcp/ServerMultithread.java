package socketstcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultithread {
    private static final int port = 5050;

    public static void main(String[] args) throws IOException {
        ServerSocket server_socket = new ServerSocket(port);
        System.out.println("Servidor Iniciado");
        while (true) {
            Socket client = null;
            try {
                client = server_socket.accept();
                System.out.println("Se ha conectado un nuevo cliente desde la IP: " + client.getInetAddress().getHostAddress());
                DataInputStream input = new DataInputStream(client.getInputStream());
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                System.out.println("Asignando un nuevo hilo");
                Thread thread = new ClientHandler(client, input, output);
                thread.start();
            } catch (Exception _exception) {
                if (client != null) client.close();
                System.out.println("Error: " + _exception.getMessage());
            }
        }
    }
}

