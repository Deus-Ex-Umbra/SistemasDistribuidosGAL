package ejercicio.n.pkg1;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.InetAddress;

public class DiplomaSystem extends UnicastRemoteObject implements IDiplomaSystem {
    private String message = "";
    public DiplomaSystem() throws RemoteException {
        super();
    }
    
    @Override
    public void createDiploma(String _data) {
        String[] datas = _data.split(",");
        String ci = datas[0], name = datas[1], first_surname = datas[2], second_surname = datas[3], date = datas[4], career = datas[5];
        message = verifyCI(ci, name, first_surname + second_surname);
        if (message.contains("no") || message.contains("[e + E]rror")) return;
    }
    
    @Override
    public String messageToServer() {
        return message;
    }
    
    @Override
    public void printConnectDevice(String _ip) {
        System.out.println("Nuevo cliente con la ip: " + _ip);
    }
    
    @Override
    public String verifyCI(String _ci, String _name, String _surname) {
        String data, message_to_server;
        try {
            Integer port = 12201;
            ISegip segip = (ISegip)Naming.lookup("rmi://127.0.0.1:" + port.toString() + "/QuerySegip/");
            data = _ci + "," + _name + "," + _surname;
            segip.viewConnectClient(InetAddress.getLocalHost().getHostAddress());
            segip.findCI(data);
            message_to_server = segip.returnMessage();
            System.out.println(message_to_server);
            if (message_to_server.equalsIgnoreCase("true")) return "Los datos del CI son correctos";
            else return "Los datos del CI no son correctos";
        } catch (MalformedURLException | RemoteException | NotBoundException ex) {
            System.out.println("Error al conectarse al servidor de Segip: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error desconocido al conectarse al servidor de segip" + ex.getMessage());
        }
        return "Los datos del CI no son correctos";
    }
    @Override
    public String verifyRude(String _rude) {
        return message;
    }
    @Override
    public String verifyDate(String _date) {
        return message;
    }
}
