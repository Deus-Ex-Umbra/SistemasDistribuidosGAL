package ejercicio.n.pkg3.pkg5;
import java.util.Scanner;

public class EjercicioN35 {
    private static final Scanner scanner = new Scanner(System.in);
    private static Agenda agendaService;

    public static void main(String[] args) {
        MySQLIntermediaryQueries.setDriver("com.mysql.cj.jdbc.Driver");
        MySQLIntermediaryQueries.connect("jdbc:mysql://localhost:3306/", "dbagenda", "root", "");
        agendaService = new Agenda(new ContactoDAO());

        while (true) {
            System.out.println("\n--- Agenda de Contactos ---");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto por nombre");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Listar todos los contactos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarContacto();
                    break;
                case 2:
                    buscarContacto();
                    break;
                case 3:
                    eliminarContacto();
                    break;
                case 4:
                    agendaService.listarContactos();
                    break;
                case 5:
                    MySQLIntermediaryQueries.close();
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void agregarContacto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Contacto contacto = new Contacto(nombre, telefono, email);
        agendaService.agregarContacto(contacto);
    }

    private static void buscarContacto() {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombreBusqueda = scanner.nextLine();
        agendaService.buscarContacto(nombreBusqueda);
    }

    private static void eliminarContacto() {
        System.out.print("Ingrese el ID del contacto a eliminar: ");
        int idEliminar = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        agendaService.eliminarContacto(idEliminar);
    }
}
