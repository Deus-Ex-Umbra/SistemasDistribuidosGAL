package ejercicio.n.pkg2;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.ArrayList;

public class MultithreadedServer {
    public static void main(String[] args) {
        int port = 5056;
        try {
            ServerSocket server = new ServerSocket(port);
            List<Thread> clients = new ArrayList<>();
            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente nuevo: " + client);
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream output = new PrintStream(client.getOutputStream());
                System.out.println("Asignando un nuevo hilo");
                Thread thread = new ClientHandler(client);
                clients.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
