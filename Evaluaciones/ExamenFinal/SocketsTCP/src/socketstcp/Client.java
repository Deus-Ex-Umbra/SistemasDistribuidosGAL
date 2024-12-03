package socketstcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String host = "localhost";
    private static final int port = 5050;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(host, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String received;
            String to_send;
            while (true) {
                received = input.readUTF();
                System.out.println(received);
                to_send = scanner.nextLine();
                output.writeUTF(to_send);
                if (to_send.equals("0")) {
                    received = input.readUTF();
                    System.out.println(received);
                    break;
                } else {
                    received = input.readUTF();
                    System.out.println(received);
                }
            }
            input.close();
            output.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
