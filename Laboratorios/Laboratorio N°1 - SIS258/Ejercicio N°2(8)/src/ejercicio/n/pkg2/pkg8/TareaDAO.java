package ejercicio.n.pkg2.pkg8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {
    private final MySQLIntermediaryQueries db;

    public TareaDAO() {
        db = new MySQLIntermediaryQueries();
        db.setDriver("com.mysql.cj.jdbc.Driver");
        db.connect("jdbc:mysql://localhost:3306/", "dbtareas", "root", ""); // Ajustar según tus credenciales
    }

    public void agregarTarea(Tarea tarea) {
        db.insertIntoTable("tareas", new Tuple<>(tarea.getDescripcion(), "descripcion"), new Tuple<>(new java.sql.Date(tarea.getFechaLimite().getTime()), "fecha_limite"), new Tuple<>(tarea.getEstado(), "estado"));
    }

    public void marcarTareaComoCompletada(int tareaId) {
        db.updateById("tareas", new Tuple<>(tareaId, "id"), new Tuple<>("COMPLETADA", "estado"));
    }

    public List<Tarea> obtenerTodasLasTareas() {
        List<Tarea> tareas = new ArrayList<>();
        try {
            ResultSet rs = db.findByColumns("tareas");
            while (rs != null && rs.next()) {
                Tarea tarea = new Tarea(
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha_limite"),
                        rs.getString("estado")
                );
                tareas.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }

    public void cerrarConexion() {
        db.close();
    }
}
