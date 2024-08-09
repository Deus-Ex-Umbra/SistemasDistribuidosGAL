package ejercicio.n.pkg3.pkg5;
import java.util.List;
class Agenda {
    private ContactoDAO contactoDAO;

    public Agenda (ContactoDAO _contactoDAO) {
        this.contactoDAO = _contactoDAO;
    }

    public void agregarContacto(Contacto contacto) {
        contactoDAO.insertar(contacto);
        System.out.println("Contacto agregado exitosamente.");
    }

    public void buscarContacto(String nombre) {
        List<Contacto> contactos = contactoDAO.buscarPorNombre(nombre);
        if (contactos.isEmpty()) {
            System.out.println("No se encontraron contactos con ese nombre.");
        } else {
            System.out.println("Contactos encontrados:");
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
    }

    public void eliminarContacto(int id) {
        contactoDAO.eliminar(id);
        System.out.println("Contacto eliminado exitosamente (si existía).");
    }

    public void listarContactos() {
        List<Contacto> contactos = contactoDAO.listarTodos();
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
        } else {
            System.out.println("Lista de contactos:");
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
    }
}
