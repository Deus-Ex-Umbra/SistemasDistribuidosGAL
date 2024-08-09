package ejercicio.n.pkg2.pkg8;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class EjercicioN28 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TareaDAO tareaDAO = new TareaDAO();
        int opcion;
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Marcar tarea como completada");
            System.out.println("3. Mostrar todas las tareas");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Introduce la descripción de la tarea: ");
                    String descripcion = scanner.next();
                    tareaDAO.agregarTarea(new Tarea(descripcion, new Date(), "PENDIENTE"));
                    break;
                case 2:
                    System.out.print("Introduce el ID de la tarea a marcar como completada: ");
                    int id = scanner.nextInt();
                    tareaDAO.marcarTareaComoCompletada(id);
                    break;
                case 3:
                    List<Tarea> tareas = tareaDAO.obtenerTodasLasTareas();
                    for (Tarea tarea : tareas) {
                        System.out.println(tarea);
                    }
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        tareaDAO.cerrarConexion();
    }

}
