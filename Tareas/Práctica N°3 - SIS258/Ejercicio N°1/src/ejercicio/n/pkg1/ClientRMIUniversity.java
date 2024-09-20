package ejercicio.n.pkg1;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientRMIUniversity {
    public static void main(String[] args) {
        try {
            Integer port = 12101;
            Scanner sc = new Scanner(System.in);
            String ci, name, f_surname, s_surname, date, career, data;
            IDiplomaSystem system = (IDiplomaSystem)Naming.lookup("rmi://127.0.0.1:" + port.toString() + "/GetDiploma/");
            system.printConnectDevice(InetAddress.getLocalHost().getHostAddress());
            System.out.println("Introduzca los siguientes datos: ");
            System.out.println("1. CI:");
            ci = sc.nextLine();
            System.out.println("2. Nombres:");
            name = sc.nextLine();
            System.out.println("3. Primer Apellido:");
            f_surname = sc.nextLine();
            System.out.println("4. Segundo Apellido:");
            s_surname = sc.nextLine();
            System.out.println("5. Fecha de Nacimiento (dd-mm-aaaa):");
            date = sc.nextLine();
            System.out.println("6. Carrera:");
            career = sc.nextLine();
            data = ci + "," + name + "," + f_surname + "," + s_surname + "," + date + "," + career;
            system.createDiploma(data);
            System.out.println(system.messageToServer());
        } catch (MalformedURLException | RemoteException | NotBoundException ex) {
            System.out.println("Error al conectarse al servidor de la universidad: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error desconocido al conectarse al servidor de la universidad" + ex.getMessage());
        }
    }
}
