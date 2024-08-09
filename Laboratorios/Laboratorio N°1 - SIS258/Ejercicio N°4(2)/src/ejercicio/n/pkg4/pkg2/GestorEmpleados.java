package ejercicio.n.pkg4.pkg2;
import java.util.List;

public class GestorEmpleados {
    private static EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public static void inicializarConexion() {
        MySQLIntermediaryQueries.setDriver("com.mysql.cj.jdbc.Driver");
        MySQLIntermediaryQueries.connect("jdbc:mysql://localhost:3306/", "empresa", "usuario", "contraseña");
    }

    public static void agregarEmpleado(Empleado empleado) {
        empleadoDAO.crear(empleado);
    }

    public static List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoDAO.obtenerTodos();
    }

    public static double calcularSalarioTotal() {
        return empleadoDAO.calcularSalarioTotal();
    }

    public static void mostrarInformacionEmpleados() {
        List<Empleado> empleados = obtenerTodosLosEmpleados();
        for (Empleado emp : empleados) {
            System.out.println(emp);
        }
    }

    public static void cerrarConexion() {
        MySQLIntermediaryQueries.close();
    }
}
