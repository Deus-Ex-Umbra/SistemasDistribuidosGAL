package ejercicio.n.pkg4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class MySQLConnector {
    private static String url = "jdbc:mysql://localhost:3306/dblibrary";
    private static String user = "root";
    private static String password = "";
    private static Connection connector;
    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connector = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return connector;
    }
}
