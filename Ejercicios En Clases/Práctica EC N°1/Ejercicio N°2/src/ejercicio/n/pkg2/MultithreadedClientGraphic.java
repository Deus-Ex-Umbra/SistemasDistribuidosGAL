package ejercicio.n.pkg2;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class MultithreadedClientGraphic extends javax.swing.JFrame {
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void initComponents(int _a) {
        hintLabel = new JLabel("Pista:");
        wordLabel = new JLabel("Palabra:");
        incorrectLettersLabel = new JLabel("Letras incorrectas:");
        attemptsLabel = new JLabel("Intentos restantes:");
        inputField = new JTextField(1);
        submitButton = new JButton("Enviar");
        imageLabel = new JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ahorcado");
        submitButton.addActionListener(evt -> submitLetter());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(hintLabel)
                        .addComponent(wordLabel)
                        .addComponent(incorrectLettersLabel)
                        .addComponent(attemptsLabel)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(inputField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(submitButton))
                        .addComponent(imageLabel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(hintLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(wordLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(incorrectLettersLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(attemptsLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(inputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(submitButton))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(imageLabel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addContainerGap())
        );

        pack();
    }
    
    private Socket client;
    private BufferedReader input;
    private PrintStream output;
    private String server_response;
    private Integer attempts;
    private JLabel hintLabel;
    private JLabel wordLabel;
    private JLabel incorrectLettersLabel;
    private JLabel attemptsLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JLabel imageLabel;


    private void initializeClient() {
        int port = 5056;
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            client = new Socket(ip, port);
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintStream(client.getOutputStream());

            new Thread(this::gameLoop).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gameLoop() {
        try {
            while (true) {
                server_response = input.readLine();
                if (server_response == null) {
                    System.out.println("Conexión cerrada por el servidor.");
                    break;
                }

                if (server_response.contains("Ganaste") || server_response.contains("Perdiste")) {
                    updateLabels(server_response, "", "", "");
                    JOptionPane.showMessageDialog(this, server_response);
                    break;
                } else {
                    updateLabels(server_response, input.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void submitLetter() {
        String letter = inputField.getText().trim();
        if (!letter.isEmpty() && letter.length() == 1) {
            output.println(letter);
            inputField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa una letra válida.");
        }
    }

    private void updateLabels(String hint, String attemptsStr) {
        SwingUtilities.invokeLater(() -> {
            hintLabel.setText(hint);

            // Intentos restantes
            if (attemptsStr != null) {
                try {
                    attempts = Integer.parseInt(attemptsStr);
                    attemptsLabel.setText("Intentos restantes: " + attempts);

                    // Cargar una imagen basada en los intentos restantes
                    loadImage(attempts);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadImage(int attempts) {
        String imagePath = "/path/to/images/image" + (10 - attempts) + ".png"; // Ajustar la ruta y el nombre del archivo
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        imageLabel.setIcon(icon);
    }

    private void updateLabels(String hint, String word, String incorrectLetters, String attemptsStr) {
        hintLabel.setText(hint);
        wordLabel.setText(word);
        incorrectLettersLabel.setText(incorrectLetters);
        attemptsLabel.setText(attemptsStr);
    }
    
    public MultithreadedClientGraphic() {
       initComponents(0);
       initializeClient();
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                java.awt.EventQueue.invokeLater(() -> new MultithreadedClientGraphic().setVisible(true));
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
