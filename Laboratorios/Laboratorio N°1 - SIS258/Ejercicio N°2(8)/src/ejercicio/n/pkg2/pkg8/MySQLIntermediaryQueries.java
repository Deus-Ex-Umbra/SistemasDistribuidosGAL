package ejercicio.n.pkg2.pkg8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLIntermediaryQueries {
    private static String driver;
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void setDriver(String driverName) {
        driver = driverName;
    }

    public static void connect(String url, String database, String user, String password) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url + database, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertIntoTable(String tableName, Tuple<Object, String>... columns) {
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        
        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i].getSecond());
            if (i < columns.length - 1) {
                sql.append(", ");
            }
        }
        
        sql.append(") VALUES (");
        
        for (int i = 0; i < columns.length; i++) {
            sql.append("?");
            if (i < columns.length - 1) {
                sql.append(", ");
            }
        }
        
        sql.append(");");
        
        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < columns.length; i++) {
                preparedStatement.setObject(i + 1, columns[i].getFirst());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static ResultSet findById(String tableName, String idColumn, Object idValue) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + idColumn + " = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, idValue);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ResultSet findByColumns(String tableName, Tuple<Object, String>... columns) {
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(tableName).append(" WHERE ");
        
        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i].getSecond()).append(" = ?");
            if (i < columns.length - 1) {
                sql.append(" AND ");
            }
        }
        
        sql.append(";");
        
        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < columns.length; i++) {
                preparedStatement.setObject(i + 1, columns[i].getFirst());
            }
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void updateById(String tableName, Tuple<Object, String> id, Tuple<Object, String>... columns) {
        StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        
        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i].getSecond()).append(" = ?");
            if (i < columns.length - 1) {
                sql.append(", ");
            }
        }
        
        sql.append(" WHERE ").append(id.getSecond()).append(" = ?;");
        
        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < columns.length; i++) {
                preparedStatement.setObject(i + 1, columns[i].getFirst());
            }
            preparedStatement.setObject(columns.length + 1, id.getFirst());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteById(String tableName, Tuple<Object, String> id) {
        String sql = "DELETE FROM " + tableName + " WHERE " + id.getSecond() + " = ?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id.getFirst());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteByColumns(String tableName, Tuple<Object, String>... columns) {
        StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ");
        
        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i].getSecond()).append(" = ?");
            if (i < columns.length - 1) {
                sql.append(" AND ");
            }
        }
        
        sql.append(";");
        
        try {
            preparedStatement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < columns.length; i++) {
                preparedStatement.setObject(i + 1, columns[i].getFirst());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
