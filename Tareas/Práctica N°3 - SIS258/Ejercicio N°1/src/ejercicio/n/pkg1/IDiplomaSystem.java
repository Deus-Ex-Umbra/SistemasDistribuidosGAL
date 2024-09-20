package ejercicio.n.pkg1;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDiplomaSystem extends Remote{
    void createDiploma(String _data) throws RemoteException;
    void printConnectDevice(String _ip) throws RemoteException;
    String messageToServer() throws RemoteException;
    String verifyCI(String _ci, String _name, String _surname) throws RemoteException;
    String verifyRude(String _rude) throws RemoteException;
    String verifyDate(String _date) throws RemoteException;
}
