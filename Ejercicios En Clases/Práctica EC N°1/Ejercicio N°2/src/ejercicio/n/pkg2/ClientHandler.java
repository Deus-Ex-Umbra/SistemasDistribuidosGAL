package ejercicio.n.pkg2;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private final Socket client;
    private AhorcadoGame ahorcado;

    public ClientHandler(Socket _client) {
        this.client = _client;
        this.ahorcado = new AhorcadoGame();
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            PrintStream output = new PrintStream(this.client.getOutputStream());
            String received;

            while (!ahorcado.isGameOver()) {
                // Enviar las pistas y las palabras en un solo mensaje de texto
                output.println("Pista: " + ahorcado.getCluePlay() 
                                + " Palabra: " + ahorcado.getCurrentGuess()
                                + " Letras no válidas: " + ahorcado.getIncorrectLetters()
                                + " Ingresa una letra: ");
                
                // Enviar los intentos disponibles de manera separada
                output.println(ahorcado.getAttemptsAvailable());
                
                // Leer la entrada del cliente
                received = input.readLine();
                if (received.equalsIgnoreCase("Exit")) {
                    System.out.println("Cerrando conexión con el cliente: " + client);
                    break;
                }

                if (received.length() == 1) {
                    char guess = received.toUpperCase().charAt(0);
                    ahorcado.guessLetter(guess);
                } else {
                    output.println("Entrada no válida");
                    continue;
                }

                if (ahorcado.isWordGuessed()) {
                    output.println("Ganaste, la palabra era: " + ahorcado.getWordPlay());
                    break;
                } else if (ahorcado.isGameOver()) {
                    output.println("Perdiste, la palabra era: " + ahorcado.getWordPlay());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
