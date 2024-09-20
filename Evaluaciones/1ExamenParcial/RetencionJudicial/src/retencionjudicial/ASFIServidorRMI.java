package retencionjudicial;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.net.InetAddress;

public class ASFIServidorRMI {
    public static void main(String[] args) {
        try {
            Integer puerto = 12001;
            String ip = InetAddress.getLocalHost().getHostAddress();
            SistemaASFI sistema = new SistemaASFI();
            LocateRegistry.createRegistry(puerto);
            Naming.bind("rmi://" + ip + ":" + puerto.toString() + "/RetencionJudicial/", sistema);
            System.out.println("Servidor ASFI iniciado correctamente");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException _ex) {
            System.out.println("Error en el servidor ASFI: " + _ex.getMessage());
        } catch (Exception _ex) {
            System.out.println("Error desconocido en el servidor ASFI: " + _ex.getMessage());
        }
    }
}