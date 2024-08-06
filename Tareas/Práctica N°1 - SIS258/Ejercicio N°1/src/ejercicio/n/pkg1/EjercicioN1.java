package ejercicio.n.pkg1;
import java.util.Scanner;
import java.math.BigInteger;
public class EjercicioN1 {
    private static Scanner sc = new Scanner(System.in);
    private static int n;
    public static void main(String[] args) {
        System.out.print("Ingrese un número: ");
        n = sc.nextInt();
        try {
            System.out.println("Su factorial es " + Factorial.calcularFactorial(n));
            System.out.println("Su fibonacci es " + Fibonacci.calcularFibonacci(n));
            System.out.println("Su sumatoria es " +  Sumatoria.sumatoria(n));
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
