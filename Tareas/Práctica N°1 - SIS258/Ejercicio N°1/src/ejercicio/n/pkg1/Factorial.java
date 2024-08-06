package ejercicio.n.pkg1;
import java.math.BigInteger;
public class Factorial {
    public static BigInteger calcularFactorial(int _n) { 
        if (_n < 0 || _n % 1 != 0) throw new IllegalArgumentException("Factorial válido sólo para números naturales");
        BigInteger result = new BigInteger("1");
        for (int i = 2; i <= _n; i++) result = result.multiply(BigInteger.valueOf(i));
        return result;
    }
}
