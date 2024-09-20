package retencionjudicial;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SistemaASFI extends UnicastRemoteObject implements ISistemaASFI {
    public SistemaASFI()throws RemoteException {
        super();
    }
    @Override
    public ArrayList<Cuenta> consultarCuentas(String _ci, String _nombres, String _apellidos) {
        return null;
    }
    
    @Override
    public Boolean retenerMonto(String _cuenta, Double _monto, String _glosa) {
        return false;
    }
    
    @Override
    public void registrarCliente(String _ip) {
        System.out.println("Nuevo Cliente con la IP: " + _ip);
    }

    private ArrayList<String> buscarEnBanco(String _ci, String _nombres, String _apellidos, Banco _banco) {
        if (_banco.equals(Banco.BCP)) {
            //TCP 
        } else {
            //UDP
        }
        return null;
    }
}
