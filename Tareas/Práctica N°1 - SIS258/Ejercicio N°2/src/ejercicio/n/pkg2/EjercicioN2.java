package ejercicio.n.pkg2;

import java.util.ArrayList;
import java.util.Scanner;

public class EjercicioN2 {

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
                    viewAll(bibliotecas);
                    break;
                case VER_ARMARIOS:
                    viewAll(armarios);
                    break;
                case VER_LIBROS:
                    viewAll(libros);
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
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el nombre de la biblioteca: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese el tamaño de la biblioteca (en m2): ");
            float tamanio = sc.nextFloat();
            createBiblioteca(id, nombre, tamanio);
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
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el código del armario: ");
            String codigo = sc.nextLine();
            System.out.print("Ingrese el ID de la biblioteca asociada: ");
            int idBiblioteca = sc.nextInt();
            createArmario(id, codigo, idBiblioteca);
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
            int id = sc.nextInt();
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
            createLibro(id, nombre, autor, editorial, anio, idArmario);
            System.out.println("Libro creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el libro: " + e.getMessage());
            sc.nextLine();
        }
    }

    public static <Type> boolean findId(ArrayList<Type> _list_of_element, int _id) {
        for (int i = 1; i <= _list_of_element.size(); i++) if (i == _id) return true;
        return false;
    }

    public static <Type> void viewAll(ArrayList<Type> _list_of_elements) {
        if (_list_of_elements.isEmpty()) {
            System.out.println("No hay elementos para mostrar.");
        } else {
            _list_of_elements.forEach(System.out::println);
        }
    }

    private static void createBiblioteca(int _idb, String _nombre, float _tamanio) {
        if (findId(bibliotecas, _idb)) {
            throw new IllegalArgumentException("Clave inválida para Biblioteca");
        }
        bibliotecas.add(new Biblioteca(_idb, _nombre, _tamanio));
    }

    private static void createArmario(int _ida, String _codigo, int _id_b) {
        if (findId(armarios, _ida)) {
            throw new IllegalArgumentException("Clave inválida para Armario");
        }
        if (!findId(bibliotecas, _id_b)) {
            throw new IllegalArgumentException("Clave Foránea no relacionada");
        }
        armarios.add(new Armario(_ida, _codigo, _id_b));
    }

    private static void createLibro(int _idl, String _nombre, String _autor, String _editorial, int _anio, int _id_a) {
        if (findId(libros, _idl)) {
            throw new IllegalArgumentException("Clave inválida para Libro");
        }
        if (!findId(armarios, _id_a)) {
            throw new IllegalArgumentException("Clave Foránea no relacionada");
        }
        libros.add(new Libro(_idl, _nombre, _autor, _editorial, _anio, _id_a));
    }
}
