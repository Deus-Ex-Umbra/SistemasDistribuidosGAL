package socketstcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Socket client;

    public ClientHandler(Socket _client, DataInputStream _input, DataOutputStream _output) {
        this.client = _client;
        this.input = _input;
        this.output = _output;
    }

    @Override
    public void run() {
        ArrayList<Integer> accumulations = new ArrayList<>();
        int sum = 0;
        int count = 0;
        try {
            while (true) {
                output.writeUTF("Escriba un número: ");
                String received = input.readUTF();
                int number;
                try {
                    number = Integer.parseInt(received);
                } catch (NumberFormatException e) {
                    output.writeUTF("Entrada inválida. Por favor ingrese un número entero.");
                    continue;
                }
                if (number != 0) {
                    sum += number;
                    accumulations.add(sum);
                    count++;
                    output.writeUTF("Suma acumulada: " + sum);
                } else {
                    if (accumulations.isEmpty()) {
                        output.writeUTF("No se acumuló nada. Acumulado: 0");
                    } else {
                        StringBuilder response = new StringBuilder("Acumulados: ");
                        for (int i = 0; i < accumulations.size(); i++) {
                            response.append("acumulado ").append(accumulations.get(i));
                            if (i < accumulations.size() - 1) {
                                response.append("\n");
                            }
                        }
                        response.append("\nSe ingresaron ").append(count).append(" números. Último acumulado: ").append(sum);
                        output.writeUTF(response.toString());
                    }
                    input.close();
                    output.close();
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        } 
    }
}
