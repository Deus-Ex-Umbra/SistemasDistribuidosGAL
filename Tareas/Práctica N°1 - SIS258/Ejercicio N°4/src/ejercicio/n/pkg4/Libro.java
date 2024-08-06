package ejercicio.n.pkg4;
public class Libro {
    private int idl;
    private String nombre;
    private String autor;
    private String editorial;
    private int anio;
    private int id_a;
    public Libro(int _idl, String _nombre, String _autor, String _editorial, int _anio, int _id_a) {
        this.idl = _idl;
        this.nombre = _nombre;
        this.autor = _autor;
        this.editorial = _editorial;
        this.anio = _anio;
        this.id_a = _id_a;
    }

    @Override
    public String toString() {
        return "Libro{" + "idl=" + idl + ", nombre=" + nombre + ", autor=" + autor + ", editorial=" + editorial + ", anio=" + anio + ", id_a=" + id_a + '}';
    }
}
