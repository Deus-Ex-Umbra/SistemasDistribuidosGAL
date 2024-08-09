package ejercicio.n.pkg4.pkg2;
import java.util.Scanner;
public class EjercicioN42 {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        GestorEmpleados.inicializarConexion();

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    agregarEmpleado();
                    break;
                case 2:
                    mostrarEmpleados();
                    break;
                case 3:
                    calcularSalarioTotal();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
        GestorEmpleados.cerrarConexion();
        scanner.close();
    }
    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Gestión de Empleados ---");
        System.out.println("1. Agregar un nuevo empleado");
        System.out.println("2. Mostrar información de todos los empleados");
        System.out.println("3. Calcular salario total de la empresa");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarEmpleado() {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el salario del empleado: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ingrese el departamento del empleado: ");
        String departamento = scanner.nextLine();

        Empleado nuevoEmpleado = new Empleado(nombre, salario, departamento);
        GestorEmpleados.agregarEmpleado(nuevoEmpleado);
        System.out.println("Empleado agregado exitosamente.");
    }

    private static void mostrarEmpleados() {
        System.out.println("\nInformación de todos los empleados:");
        GestorEmpleados.mostrarInformacionEmpleados();
    }

    private static void calcularSalarioTotal() {
        double salarioTotal = GestorEmpleados.calcularSalarioTotal();
        System.out.printf("\nSalario total de la empresa: $%.2f%n", salarioTotal);
    }
}
