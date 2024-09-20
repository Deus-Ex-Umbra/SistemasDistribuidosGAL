package retencionjudicial;

import java.util.ArrayList;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class BancoMercantilUDP implements IBanco{
    private static ArrayList<Cuenta> cuentas_mercantil;
    public static void main(String[] args) {
        cuentas_mercantil = null;
        Integer puerto = 12301;
        try {
           DatagramSocket socket_udp = new DatagramSocket(puerto);
           byte[] buffer = new byte[1024];
           while (true) {
               DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
               socket_udp.receive(peticion);
               System.out.println("Ip: " + peticion.getAddress());
               System.out.println("Puerto: " + peticion.getPort());
               String comando = new String(peticion.getData());
               String[] ejecutable = comando.split(":");
               String respuesta = "";
               Integer valor = Integer.valueOf(comando.trim());
               if (ejecutable[0].equals("Buscar")) {
                   String[] datos = ejecutable[i].split("-");
                   for (String resultado : buscar(datos[0], datos[1], datos[2])) {
                       
                   }
               } else if (ejecutable[0].equals("Congelar")) {
                   
               } else {
                   Integer i_respuesta = valor * 2;
                   respuesta = "Error";
               }
               byte[] mensaje = respuesta.getBytes(); 
               DatagramPacket al_cliente = new DatagramPacket(mensaje, respuesta.length(), peticion.getAddress(), peticion.getPort());
               socket_udp.send(al_cliente);
           }
        } catch (SocketException _ex) {
            System.out.println("Error Socket: " + _ex.getMessage());
        } catch (IOException _ex) {
            System.out.println("Error Io: " + _ex.getMessage());
        }
    }
    
    @Override
    public ArrayList<String> buscar(String _ci, String _nombres, String _apellidos) {
        ArrayList<String> resultado = new ArrayList<String>();
        if (cuentas_mercantil != null) {
            for (Cuenta cuenta : cuentas_mercantil) {
                String ci = cuenta.getCi(), nombre = cuenta.getNombres(), apellidos = cuenta.getApellidos();
                if (ci.equalsIgnoreCase(_ci) && nombre.equalsIgnoreCase(_nombres) && apellidos.equalsIgnoreCase(_apellidos)) resultado.add(cuenta.toString());
            }
            if (resultado.isEmpty()) return null;
            return resultado;
        }
        return null;
    }
    
    @Override
    public Boolean congelar(String _nro_cuenta, Double _monto) {
        if (cuentas_mercantil != null) {
            for (Cuenta cuenta : cuentas_mercantil) {
                String nro = cuenta.getNumeroCuenta();
                Double saldo = cuenta.getSaldo();
                if (nro.equals(_nro_cuenta)) {
                    if (saldo >= _monto) {
                        cuenta.setSaldo(saldo - _monto);
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
}
