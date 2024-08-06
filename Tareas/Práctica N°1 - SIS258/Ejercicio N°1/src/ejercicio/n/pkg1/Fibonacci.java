package ejercicio.n.pkg1;
public class Fibonacci {
    public static long calcularFibonacci(int _n) {
        if (_n < 0) throw new IllegalArgumentException("Números negativos no tienen fibonacci");
        long a = 0, b = 1;
        if (_n == 0) return a;
        for (int i = 2; i <= _n; i ++) {
            long aux = a + b;
            a = b;
            b = aux;
        }
        return b;
    }
}
