package práctica.ec.n.pkg2;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    
    public static void main(String[] args) {
        try {
            // Crear una instancia del juego Ahorcado
            Ahorcado ahorcado = new Ahorcado();
            
            // Inicializar el juego antes de exponerlo al cliente
            ahorcado.inciar();
            
            // Crear el registro RMI en el puerto 1099
            LocateRegistry.createRegistry(1099);
            
            // Publicar el objeto Ahorcado en el registro con el nombre "JuegoAhorcado"
            Naming.bind("JuegoAhorcado", ahorcado);
            
            System.out.println("Servidor del Juego Ahorcado iniciado y esperando conexiones...");
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    } 
}