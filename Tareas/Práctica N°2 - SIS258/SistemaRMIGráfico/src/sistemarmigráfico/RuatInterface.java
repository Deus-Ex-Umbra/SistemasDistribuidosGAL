package sistemarmigráfico;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RuatInterface extends Remote {
    String consultarDeudas(String ci) throws RemoteException;
    String procesarPago(String ci, int year, String taxType, double amount) throws RemoteException;
}
