package ejercicio.n.pkg4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibroDAO {
    private static final String INSERT_QUERY = "INSERT INTO libro (nombre, autor, editorial, anio, id_a) VALUES (?, ?, ?, ?, ?)";
    private static Connection connection = MySQLConnector.createConnection();
    private static ArrayList<Libro> libros = new ArrayList<>();

    public static void addLibro(String id, String nombre, String autor, String editorial, int anio, int id_a) {

        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, id);
            statement.setString(2, nombre);
            statement.setString(3, autor);
            statement.setString(4, editorial);
            statement.setInt(5, anio);
            statement.setInt(6, id_a);
            statement.executeUpdate();

            libros.add(new Libro(Integer.parseInt(id), nombre, autor, editorial, anio, id_a));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAll() {
        if (libros.isEmpty()) {
            System.out.println("No hay elementos para mostrar.");
        } else {
            libros.forEach(System.out::println);
        }
    }
}
