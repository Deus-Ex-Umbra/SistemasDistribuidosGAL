package ejercicio.n.pkg3.pkg5;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class ContactoDAO {
    private static final String TABLE_NAME = "contactos";

    public ContactoDAO() {
    }
    
    public void insertar(Contacto contacto) {
        MySQLIntermediaryQueries.insertIntoTable(TABLE_NAME,
                new Tuple<>(contacto.getNombre(), "nombre"),
                new Tuple<>(contacto.getTelefono(), "telefono"),
                new Tuple<>(contacto.getEmail(), "email"));
    }
    public List<Contacto> buscarPorNombre(String nombre) {
        List<Contacto> contactos = new ArrayList<>();
        ResultSet rs = MySQLIntermediaryQueries.findByColumns(TABLE_NAME, 
                new Tuple<>(nombre, "nombre"));
        try {
            while (rs.next()) {
                contactos.add(new Contacto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }
    public void eliminar(int id) {
        MySQLIntermediaryQueries.deleteById(TABLE_NAME, new Tuple<>(id, "id"));
    }
    public List<Contacto> listarTodos() {
        List<Contacto> contactos = new ArrayList<>();
        ResultSet rs = MySQLIntermediaryQueries.findByColumns(TABLE_NAME);
        try {
            while (rs.next()) {
                contactos.add(new Contacto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }
}
