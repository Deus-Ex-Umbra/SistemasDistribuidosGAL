package sistemarmigráfico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class UsuarioTCP extends javax.swing.JFrame {
    private Socket client;
    private BufferedReader from_server;
    private PrintStream to_server;
    public UsuarioTCP() {
        initComponents();
        initializeConnection();
    }
    private void initializeConnection() {
        String ip = "localhost"; // IP del Banco
        int port = 5000;
        try {
            client = new Socket(ip, port);
            to_server = new PrintStream(client.getOutputStream());
            from_server = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        button_mostrar = new javax.swing.JButton();
        button_pagar = new javax.swing.JButton();
        button_salir = new javax.swing.JButton();
        title = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_mostrar.setText("Mostrar Deudas");
        button_mostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_mostrarMouseClicked(evt);
            }
        });
        button_mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_mostrarActionPerformed(evt);
            }
        });

        button_pagar.setText("Pagar Deudas");
        button_pagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_pagarMouseClicked(evt);
            }
        });

        button_salir.setText("Salir");
        button_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_salirMouseClicked(evt);
            }
        });

        title.setText("Sistema de Pagos RUAT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_mostrar)
                    .addComponent(title)
                    .addComponent(button_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(button_mostrar)
                .addGap(18, 18, 18)
                .addComponent(button_pagar)
                .addGap(18, 18, 18)
                .addComponent(button_salir)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_mostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_mostrarActionPerformed

    private void button_mostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_mostrarMouseClicked
        try {
            String ci = JOptionPane.showInputDialog(this, "Ingrese su CI:");
            to_server.println("Deuda:" + ci);
            String response = from_server.readLine();
            JOptionPane.showMessageDialog(this, "Deudas: " + response);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al obtener deudas: " + ex.getMessage());
        }
    }//GEN-LAST:event_button_mostrarMouseClicked

    private void button_pagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_pagarMouseClicked
        try {
            String ci = JOptionPane.showInputDialog(this, "Ingrese su CI:");
            String year = JOptionPane.showInputDialog(this, "Ingrese el año de la deuda:");
            String taxType = JOptionPane.showInputDialog(this, "Ingrese el tipo de impuesto (Vehículo o Casa):");
            String amount = JOptionPane.showInputDialog(this, "Ingrese el monto a pagar:");

            to_server.println("Pagar:" + ci + "," + year + "," + taxType + "," + amount);
            String response = from_server.readLine();
            JOptionPane.showMessageDialog(this, "Resultado del pago: " + response);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar el pago: " + ex.getMessage());
        }
    }//GEN-LAST:event_button_pagarMouseClicked

    private void button_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_salirMouseClicked
        try {
            client.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + ex.getMessage());
        }
        System.exit(0);
    }//GEN-LAST:event_button_salirMouseClicked
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsuarioTCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioTCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioTCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioTCP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarioTCP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_mostrar;
    private javax.swing.JButton button_pagar;
    private javax.swing.JButton button_salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}

