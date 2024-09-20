package retencionjudicial;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BancoBCPTCP implements IBanco {
    private static ArrayList<Cuenta> cuentas_bcp;
    public static void main(String[] args) {
        Integer puerto = 12201;
        cuentas_bcp.add(new Cuenta(Banco.BCP, "657654", "11021654", "Juan Perez", "Segovia", 200.0));
        try (ServerSocket servidor = new ServerSocket(puerto);){
            System.out.println("Se inició la conexión");
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");
            BufferedReader desde_cliente = new BufferedReader(
                    new InputStreamReader(
                            cliente.getInputStream()
                    )
            );
            PrintStream al_cliente;
            while (true) {
                
            }
        } catch (IOException _ex) {
            System.out.println(_ex.getMessage());
        } catch (Exception _ex) {
            System.out.println(_ex.getMessage());
        }
    }
    @Override
    public ArrayList<String> buscar(String _ci, String _nombres, String _apellidos) {
        ArrayList<String> resultado = new ArrayList<String>();
        if (cuentas_bcp != null) {
            for (Cuenta cuenta : cuentas_bcp) {
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
        if (cuentas_bcp != null) {
            for (Cuenta cuenta : cuentas_bcp) {
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
