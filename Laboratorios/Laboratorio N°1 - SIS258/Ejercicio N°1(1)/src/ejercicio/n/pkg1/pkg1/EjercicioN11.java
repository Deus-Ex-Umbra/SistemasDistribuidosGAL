package ejercicio.n.pkg1.pkg1;
import java.util.Scanner;
public class EjercicioN11 {
    private enum opciones {
        AREA,
        PERIMETRO
    }
    private static int opcion;
    private static float base;
    private static float altura;
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.println("1. Calcular área de un Rectángulo");
                System.out.println("2. Calcular perímetro de un Rectángulo");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                if (opcion == 0) {
                    System.out.println("Gracias por usar el programa");
                    return;
                }
                if (opcion > opciones.values().length) continue;
                sc.nextLine();
                System.out.print("Valor de la base: ");
                base = sc.nextFloat();
                System.out.print("Valor de la altura: ");
                altura = sc.nextFloat();
                switch (opciones.values()[opcion - 1]) {
                    case AREA:
                        System.out.println("Área: " + CalculosRectangulo.calcularAreaRectangulo(base, altura));
                        break;
                    case PERIMETRO:
                        System.out.println("Perímetro: " + CalculosRectangulo.calcularAreaRectangulo(base, altura));
                        CalculosRectangulo.calcularPerimetroRectangulo(base, altura);
                        break;
                }
            } while(true);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
