package ejercicio.n.pkg1;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

public class ServerRMIUniversity {
    public static void main(String[] args) {
        try {
            Integer port = 12101;
            DiplomaSystem system = new DiplomaSystem();
            LocateRegistry.createRegistry(port);
            Naming.bind("rmi://127.0.0.1:" + port.toString() + "/GetDiploma/", system);
        } catch (MalformedURLException | AlreadyBoundException | RemoteException ex) {
            System.out.println("Error en el Servidor de la Universidad: " + ex.getMessage());
        }
    }
}
