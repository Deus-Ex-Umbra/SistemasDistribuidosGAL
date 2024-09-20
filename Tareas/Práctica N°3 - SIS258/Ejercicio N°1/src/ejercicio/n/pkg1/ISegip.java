package ejercicio.n.pkg1;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ISegip extends Remote{
    void findCI(String _data) throws RemoteException;
    void viewConnectClient(String _ip) throws RemoteException;
    String returnMessage() throws RemoteException;
}
