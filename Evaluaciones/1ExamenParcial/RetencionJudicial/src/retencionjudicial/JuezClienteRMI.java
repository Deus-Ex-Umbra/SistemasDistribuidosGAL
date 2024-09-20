package retencionjudicial;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.ArrayList;

public class JuezClienteRMI {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);){
            ArrayList<Cuenta> cuentas;
            Integer opcion, puerto = 12001;
            String ip_servidor = "127.0.0.1", ip = InetAddress.getLocalHost().getHostAddress();
            ISistemaASFI sistema = (ISistemaASFI)Naming.lookup("rmi://" + ip_servidor + ":" + puerto.toString() + "/RetencionJudicial/");
            sistema.registrarCliente(ip);
            while (true) {
                System.out.println("1. Listar todas las Cuentas: ");
                System.out.println("2. Retener Monto: ");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                while (opcion < 0 || opcion > 2) {
                    System.out.print("Seleccione una opción: ");
                    opcion = sc.nextInt();
                }
                if (opcion == 0) {
                    System.out.println("Gracias por usar el programa");
                    break;
                }
                sc.nextLine(); //Limpiar el Buffer
                switch (opcion) {
                    case 1:
                        String ci, nombres, apellidos;
                        System.out.println("Escriba los siguientes datos: ");
                        System.out.print("1. Carnet de Identidad: ");
                        ci = sc.nextLine();
                        System.out.print("2. Nombre(s): ");
                        nombres = sc.nextLine();
                        System.out.print("3. Apellido(s): ");
                        apellidos = sc.nextLine();
                        System.out.println("-------------------------------------------------");
                        cuentas = sistema.consultarCuentas(ci, nombres, apellidos);
                        if (cuentas != null) {
                            System.out.println("Todas las cuentas: ");
                            Integer index = 0;
                            for (Cuenta cuenta : cuentas) {
                                System.out.print("Mercantil: ");
                                if (cuenta.getBanco().equals(Banco.Mercantil)) {
                                    index++;
                                    System.out.println(index.toString() + ". " + cuenta);
                                }
                            }
                            if (index == 0) System.out.println("[]");
                            index = 0;
                            for (Cuenta cuenta : cuentas) {
                                System.out.print("BCP: ");
                                if (cuenta.getBanco().equals(Banco.BCP)) {
                                    index++;
                                    System.out.println(index.toString() + ". " + cuenta);
                                }
                            }
                            if (index == 0) System.out.println("[]");
                        } else {
                            System.out.println("No existen cuentas con esos datos");
                        }
                        System.out.println("-------------------------------------------------");
                     break;
                    case 2:
                        String nro_cuenta, glosa;
                        Double monto;
                        System.out.println("Escriba los siguientes datos: ");
                        System.out.print("1. Nro. de Cuenta: ");
                        nro_cuenta = sc.nextLine();
                        System.out.print("2. Monto: ");
                        monto = sc.nextDouble();
                        sc.nextLine(); //Limpiar el Buffer
                        System.out.println("3. Glosa: ");
                        glosa = sc.nextLine();
                        Boolean transaccion_completada = sistema.retenerMonto(nro_cuenta, monto, glosa);
                        if (transaccion_completada) System.out.println("Transacción Realizada con Éxito");
                        else System.out.println("No se pudo realizar la retención");
                }
            }
        } catch(MalformedURLException | RemoteException | NotBoundException  _ex) {
            System.out.println("Error al conectar a servidor ASFI: " + _ex.getMessage());
        } catch (Exception _ex) {
            System.out.println("Error desconocido al conectar a servidor ASFI: " + _ex.getMessage());
        }
    }
}


