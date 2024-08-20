package ejercicio.n.pkg2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultithreadedClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int port = 5056;
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket client = new Socket(ip, port);
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream output = new PrintStream(client.getOutputStream());
            String to_server = null;
            String server_response;
            String attempts_str;
            Integer attempts;
            while (true) {
                server_response = input.readLine();
                
                if (server_response == null) {
                    System.out.println("Conexión cerrada por el servidor.");
                    break;
                }
                System.out.println(server_response);
                if (server_response.contains("Ganaste") || server_response.contains("Perdiste")) {
                    to_server = "Exit";
                } else {
                    attempts_str = input.readLine();
                    if (attempts_str != null) {
                        try {
                            attempts = Integer.parseInt(attempts_str);
                        } catch (NumberFormatException e) {
                            System.out.println("Error al intentar leer los intentos restantes: " + attempts_str);
                        }
                    }
                    to_server = sc.nextLine();
                    output.println(to_server);
                }
                if (to_server != null && to_server.equalsIgnoreCase("Exit")) {
                    System.out.println("Cerrando esta conexión: " + client);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
