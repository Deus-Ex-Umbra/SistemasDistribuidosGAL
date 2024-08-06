package ejercicio.n.pkg3;
import java.util.Scanner;

public class EjercicioN3 {
    private static final char VACIO = '-';
    private static final char JUGADOR_X = 'X';
    private static final char JUGADOR_O = 'O';
    private static char[][] tablero = {
        {VACIO, VACIO, VACIO},
        {VACIO, VACIO, VACIO},
        {VACIO, VACIO, VACIO}
    };
    private static Scanner sc = new Scanner(System.in);
    private static char jugadorActual = JUGADOR_X;
    public static void main(String[] args) {
        boolean juegoActivo = true;
        System.out.println("¡Bienvenidos al juego de Tres en Raya!");
        imprimirTablero();

        while (juegoActivo) {
            System.out.println("Turno del jugador " + jugadorActual + ". Ingresa fila y columna (0-2):");
            int fila = sc.nextInt();
            int columna = sc.nextInt();
            if (esMovimientoValido(fila, columna)) {
                realizarMovimiento(fila, columna);
                imprimirTablero();
                if (esVictoria(jugadorActual)) {
                    System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                    juegoActivo = false;
                } else if (esEmpate()) {
                    System.out.println("¡El juego ha terminado en empate!");
                    juegoActivo = false;
                } else {
                    cambiarJugador();
                }
            } else {
                System.out.println("Movimiento inválido. Intenta de nuevo.");
            }
        }
        System.out.println("¡Gracias por jugar!");
    }
    private static void imprimirTablero() {
        for (char[] fila : tablero) {
            for (char celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
    private static boolean esMovimientoValido(int fila, int columna) {
        if (fila < 0 || fila >= 3 || columna < 0 || columna >= 3) {
            return false;
        }
        return tablero[fila][columna] == VACIO;
    }
    private static void realizarMovimiento(int fila, int columna) {
        tablero[fila][columna] = jugadorActual;
    }
    private static void cambiarJugador() {
        jugadorActual = (jugadorActual == JUGADOR_X) ? JUGADOR_O : JUGADOR_X;
    }
    private static boolean esVictoria(char jugador) {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] == jugador && tablero[1][j] == jugador && tablero[2][j] == jugador) {
                return true;
            }
        }
        if (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) {
            return true;
        }
        if (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador) {
            return true;
        }
        return false;
    }
    private static boolean esEmpate() {
        for (char[] fila : tablero) {
            for (char celda : fila) {
                if (celda == VACIO) {
                    return false;
                }
            }
        }
        return true;
    }
}