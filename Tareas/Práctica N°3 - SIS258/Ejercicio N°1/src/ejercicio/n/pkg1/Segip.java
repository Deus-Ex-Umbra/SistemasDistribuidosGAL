package ejercicio.n.pkg1;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class Segip extends UnicastRemoteObject implements ISegip {
    String message_to_client;
    public Segip() throws RemoteException{
        super();
    }
    
    @Override
    public void findCI(String _data) {
        message_to_client = "Conexión exitosa";
    }
    
    @Override
    public void viewConnectClient(String _ip) {
        System.out.println("Ip del Cliente: " + _ip);
    }
    
    @Override
    public String returnMessage() {
        return message_to_client;
    }
}
