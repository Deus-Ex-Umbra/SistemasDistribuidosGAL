package práctica.ec.n.pkg2;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAhorcado extends Remote   {
    public void inciar() throws RemoteException;
    public Boolean esJuegoTerminado() throws RemoteException;
    public Respuesta adivinarLetra(char letra) throws RemoteException;
    public Respuesta adivinarPalabra(String palabra) throws RemoteException;
    
}