package ejercicio.n.pkg4;

import java.util.ArrayList;
import java.util.Scanner;

public class EjercicioN4 {
private static ArrayList<Biblioteca> bibliotecas = new ArrayList<>();
    private static ArrayList<Armario> armarios = new ArrayList<>();
    private static ArrayList<Libro> libros = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int opcion;
    private enum Opciones {
        CREAR_BIBLIOTECA,
        CREAR_ARMARIO,
        CREAR_LIBRO,
        VER_BIBLIOTECAS,
        VER_ARMARIOS,
        VER_LIBROS
    }
    public static void main(String[] args) {
        do {
            System.out.println("Menú:");
            System.out.println("1. Crear biblioteca");
            System.out.println("2. Crear armario");
            System.out.println("3. Crear libro");
            System.out.println("4. Ver todas las bibliotecas");
            System.out.println("5. Ver todos los armarios");
            System.out.println("6. Ver todos los libros");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion < 0 || opcion >= Opciones.values().length) {
                    System.out.println("Opción seleccionada fuera de rango");
                    continue;
                }
            } catch (Exception exception) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                sc.nextLine();
                continue;
            }
            if (opcion == 0) {
                System.out.println("Gracias por usar el programa");
                return;
            }
            switch (Opciones.values()[opcion - 1]) {
                case CREAR_BIBLIOTECA:
                    formularioCrearBiblioteca();
                    break;
                case CREAR_ARMARIO:
                    formularioCrearArmario();
                    break;
                case CREAR_LIBRO:
                    formularioCrearLibro();
                    break;
                case VER_BIBLIOTECAS:
                    BibliotecaDAO.viewAll();
                    break;
                case VER_ARMARIOS:
                    ArmarioDAO.viewAll();
                    break;
                case VER_LIBROS:
                    LibroDAO.viewAll();
                    break;
            }
            System.out.println("Presiona cualquier tecla para continuar...");
            new Scanner(System.in).nextLine();
            System.out.flush();
        } while (true);
    }
    private static void formularioCrearBiblioteca() {
        try {
            System.out.println("Crear nueva Biblioteca");
            System.out.print("Ingrese el ID de la biblioteca: ");
            String id = sc.nextLine();
            sc.nextLine();
            System.out.print("Ingrese el nombre de la biblioteca: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese el tamaño de la biblioteca (en m2): ");
            float tamanio = sc.nextFloat();
            BibliotecaDAO.addBiblioteca(id, nombre, tamanio);
            System.out.println("Biblioteca creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear la biblioteca: " + e.getMessage());
            sc.nextLine();
        }
    }

    private static void formularioCrearArmario() {
        try {
            System.out.println("Crear nuevo Armario");
            System.out.print("Ingrese el ID del armario: ");
            String id = sc.nextLine();
            sc.nextLine();
            System.out.print("Ingrese el código del armario: ");
            String codigo = sc.nextLine();
            System.out.print("Ingrese el ID de la biblioteca asociada: ");
            int idBiblioteca = sc.nextInt();
            ArmarioDAO.addArmario(id, codigo, idBiblioteca);
            System.out.println("Armario creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el armario: " + e.getMessage());
            sc.nextLine();
        }
    }

    private static void formularioCrearLibro() {
        try {
            System.out.println("Crear nuevo Libro");
            System.out.print("Ingrese el ID del libro: ");
            String id = sc.nextLine();
            sc.nextLine();
            System.out.print("Ingrese el nombre del libro: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese el autor del libro: ");
            String autor = sc.nextLine();
            System.out.print("Ingrese la editorial del libro: ");
            String editorial = sc.nextLine();
            System.out.print("Ingrese el año de publicación del libro: ");
            int anio = sc.nextInt();
            System.out.print("Ingrese el ID del armario asociado: ");
            int idArmario = sc.nextInt();
            LibroDAO.addLibro(id, nombre, autor, editorial, anio, idArmario);
            System.out.println("Libro creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el libro: " + e.getMessage());
            sc.nextLine();
        }
    }
}
