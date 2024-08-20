package ejercicio.n.pkg1;
import java.math.BigInteger;

public class Operation {
    private static long number = 0;
    public static void setNumber(long _number) {
        number = _number;
    }
    public static BigInteger factorial() {
        if (number < 0 || number % 1 != 0) throw new IllegalArgumentException("Factorial válido sólo para números naturales");
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= number; i++) result = result.multiply(BigInteger.valueOf(i));
        return result;
    }
    public static BigInteger fibonacci() {
        if (number < 0) throw new IllegalArgumentException("No existe Fibonacci de números negativos");
        BigInteger a = BigInteger.ZERO, b = BigInteger.ONE;
        if (number == 0) return a;
        for (int i = 2; i <= number; i ++) {
            BigInteger aux = a.add(b);
            a = b;
            b = aux;
        }
        return b;
    }
    public static BigInteger summatory() {
        if (number < 0) throw new IllegalArgumentException("No existe sumatoria de un número negativo");
        BigInteger n = new BigInteger(Long.toString(number));
        return n.multiply(n.add(BigInteger.ONE)).divide(BigInteger.TWO); 
    }
    public static long getNumber() {
        return number;
    }
}
