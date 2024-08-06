package ejercicio.n.pkg1;
public class Sumatoria {
    public static long sumatoria(int _n) {
        if (_n < 0) throw new IllegalArgumentException("No existe sumatoria de un número negativo");
        return (long)_n * (_n + 1) / 2;
    }
}
