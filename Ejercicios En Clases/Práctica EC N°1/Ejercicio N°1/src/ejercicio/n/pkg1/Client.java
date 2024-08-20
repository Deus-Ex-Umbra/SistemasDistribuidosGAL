package ejercicio.n.pkg1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int port = 5002;
        try (Socket client = new Socket("localhost", port)) {
            String sent;
            String result;
            PrintStream to_server = new PrintStream(client.getOutputStream());
            BufferedReader from_server = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()
                    )
            );
            while (true) {
                System.out.println("1. Calcular factorial.");
                System.out.println("2. Calcular fibonacci.");
                System.out.println("3. Calcular sumatoria.");
                System.out.println("4. Cambiar valor de N.");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                sent = sc.nextLine();
                to_server.println(sent);
                if (sent.equals("0")) {
                    break;
                }
                if (sent.equals("4")) {
                    System.out.print("Escriba un número entero: ");
                    sent = sc.nextLine();
                to_server.println(sent);
                }
                result = from_server.readLine();
                System.out.println("Resultado: " + result);
            }
            
        } catch (IOException _ex) {
            _ex.printStackTrace();
        }
    }
}
