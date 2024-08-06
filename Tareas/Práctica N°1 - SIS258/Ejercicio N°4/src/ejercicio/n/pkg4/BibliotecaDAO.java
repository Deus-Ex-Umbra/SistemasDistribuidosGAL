package ejercicio.n.pkg4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BibliotecaDAO {
    private static final String INSERT_QUERY = "INSERT INTO biblioteca (nombre, tamanio) VALUES (?, ?)";
    private static Connection connection = MySQLConnector.createConnection();
    private static ArrayList<Biblioteca> bibliotecas = new ArrayList<>();

    public static void addBiblioteca(String id, String nombre, float tamanio) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, id);
            statement.setString(2, nombre);
            statement.setFloat(3, tamanio);
            statement.executeUpdate();

            bibliotecas.add(new Biblioteca(Integer.parseInt(id), nombre, tamanio));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAll() {
        if (bibliotecas.isEmpty()) {
            System.out.println("No hay elementos para mostrar.");
        } else {
            bibliotecas.forEach(System.out::println);
        }
    }
}
