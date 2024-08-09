package ejercicio.n.pkg4.pkg2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoDAO {
    private static final String TABLE_NAME = "empleados";
    private List<Empleado> empleados = new ArrayList<>();

    public void crear(Empleado empleado) {
        MySQLIntermediaryQueries.insertIntoTable(TABLE_NAME,
                new Tuple<>(empleado.getNombre(), "nombre"),
                new Tuple<>(empleado.getSalario(), "salario"),
                new Tuple<>(empleado.getDepartamento(), "departamento"));
        ResultSet rs = MySQLIntermediaryQueries.findByColumns(TABLE_NAME,
                new Tuple<>(empleado.getNombre(), "nombre"),
                new Tuple<>(empleado.getSalario(), "salario"),
                new Tuple<>(empleado.getDepartamento(), "departamento"));
        try {
            if (rs.next()) {
                empleado.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        empleados.add(empleado);
    }

    public Empleado obtenerPorId(int id) {
        Optional<Empleado> empleadoEnMemoria = empleados.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        
        if (empleadoEnMemoria.isPresent()) {
            return empleadoEnMemoria.get();
        }

        ResultSet rs = MySQLIntermediaryQueries.findById(TABLE_NAME, "id", id);
        try {
            if (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("salario"),
                        rs.getString("departamento")
                );
                empleados.add(empleado);
                return empleado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Empleado> obtenerTodos() {
        if (!empleados.isEmpty()) {
            return new ArrayList<>(empleados);
        }

        ResultSet rs = MySQLIntermediaryQueries.findByColumns(TABLE_NAME);
        try {
            while (rs.next()) {
                empleados.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("salario"),
                        rs.getString("departamento")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(empleados);
    }

    public void actualizar(Empleado empleado) {
        MySQLIntermediaryQueries.updateById(TABLE_NAME,
                new Tuple<>(empleado.getId(), "id"),
                new Tuple<>(empleado.getNombre(), "nombre"),
                new Tuple<>(empleado.getSalario(), "salario"),
                new Tuple<>(empleado.getDepartamento(), "departamento"));
        
        // Actualizar en la lista en memoria
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == empleado.getId()) {
                empleados.set(i, empleado);
                break;
            }
        }
    }

    public void eliminar(int id) {
        MySQLIntermediaryQueries.deleteById(TABLE_NAME, new Tuple<>(id, "id"));
        empleados.removeIf(e -> e.getId() == id);
    }

    public double calcularSalarioTotal() {
        return obtenerTodos().stream().mapToDouble(Empleado::getSalario).sum();
    }
}