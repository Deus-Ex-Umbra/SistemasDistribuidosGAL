package ejercicio.n.pkg1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        int port = 5002;
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            System.out.println("Servidor Iniciado");
            Socket client = server.accept();
            while (true) {
                BufferedReader from_client = new BufferedReader(
                        new InputStreamReader(
                                client.getInputStream()
                        )
                );
                PrintStream to_client = new PrintStream(client.getOutputStream());

                String received = from_client.readLine();
                Integer option = Integer.parseInt(received);
                
                if (option == 0) {
                    client.close();
                    break;
                }
                String result = "";

                switch(option) {
                    case 1:
                        result = Operation.factorial().toString();
                        break;
                    case 2:
                        result = Operation.fibonacci().toString();
                        break;
                    case 3:
                        result = Operation.summatory().toString();
                        break;
                    case 4:
                        received = from_client.readLine();
                        Long number = Long.parseLong(received);
                        Operation.setNumber(number);
                        result = "Número cambiado a " + Operation.getNumber();
                        break;
                }
                to_client.println(result);
            }
        } catch(IOException _ex) {
            _ex.printStackTrace();
        } catch(Exception _ex) {
            _ex.printStackTrace();
        }
    }
}
