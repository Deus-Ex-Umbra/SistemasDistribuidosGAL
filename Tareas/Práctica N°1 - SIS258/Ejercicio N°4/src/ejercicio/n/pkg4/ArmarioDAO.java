package ejercicio.n.pkg4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArmarioDAO {
    private static final String INSERT_QUERY = "INSERT INTO armario (codigo, tipo, id_b) VALUES (?, ?, ?)";
    private static Connection connection = MySQLConnector.createConnection();
    private static ArrayList<Armario> armarios = new ArrayList<>();

    public static void addArmario(String codigo, String tipo, int id_b) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, codigo);
            statement.setString(2, tipo);
            statement.setInt(3, id_b);
            statement.executeUpdate();

            armarios.add(new Armario(Integer.parseInt(codigo), tipo, id_b));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewAll() {
        if (armarios.isEmpty()) {
            System.out.println("No hay elementos para mostrar.");
        } else {
            armarios.forEach(System.out::println);
        }
    }
}
