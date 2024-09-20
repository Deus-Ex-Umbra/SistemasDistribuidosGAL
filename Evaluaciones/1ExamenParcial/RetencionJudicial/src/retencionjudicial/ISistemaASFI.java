package retencionjudicial;
import java.util.ArrayList;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ISistemaASFI extends Remote{
    ArrayList<Cuenta> consultarCuentas(String _ci, String _nombres, String _apellidos)throws RemoteException;
    Boolean retenerMonto(String _cuenta, Double _monto, String _glosa) throws RemoteException;
    void registrarCliente(String _ip) throws RemoteException;
}
