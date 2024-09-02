package práctica.ec.n.pkg2;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {
        try {
            Scanner sc = new Scanner(System.in);
            IAhorcado ahorcado;
            ahorcado = (IAhorcado) Naming.lookup("rmi://localhost/JuegoAhorcado");
            ahorcado.inciar(); // Llama al método para iniciar el juego

            System.out.println("Juego del Ahorcado");
            Respuesta respuesta;
            
            while (!ahorcado.esJuegoTerminado()) {
                System.out.println("Adivine la palabra o una letra: ");
                String entrada = sc.nextLine();
                
                if (entrada.length() == 1) {
                    // Si es una sola letra, llama a adivinarLetra
                    respuesta = ahorcado.adivinarLetra(entrada.charAt(0));
                } else {
                    // Si es una palabra completa, llama a adivinarPalabra
                    respuesta = ahorcado.adivinarPalabra(entrada);
                }
                
                System.out.println(respuesta.toString());
            }
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
