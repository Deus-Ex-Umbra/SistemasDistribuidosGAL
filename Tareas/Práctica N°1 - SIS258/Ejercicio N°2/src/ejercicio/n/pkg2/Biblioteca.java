package ejercicio.n.pkg2;
public class Biblioteca {
    private int idb;
    private String nombre;
    private float tamanio;

    public Biblioteca(int _idb, String _nombre, float _tamanio) {
        this.idb = _idb;
        this.nombre = _nombre;
        this.tamanio = _tamanio;
    }
    @Override
    public String toString() {
        return "Biblioteca{" + "idb=" + idb + ", nombre=" + nombre + ", tamanio=" + tamanio + '}';
    }
}
