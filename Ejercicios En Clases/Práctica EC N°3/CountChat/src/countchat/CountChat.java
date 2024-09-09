package countchat;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;
import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


public class CountChat extends ReceiverAdapter{
    JChannel channel;
    private String user_name;
    private HashMap<Integer, Integer> locations;
    private Integer juan = 0;
    private Integer pedro = 0;
    private Integer maria = 0;

    public CountChat(String user_name) {
        this.user_name = user_name;
        locations = new HashMap<Integer, Integer>(){{
            put(1, 0);
            put(2, 0);
            put(3, 0);
            put(4, 0);
            put(5, 0);
            put(6, 0);
            put(7, 0);
            put(8, 0);
            put(9, 0);
        }};
    }
    final List<String> state=new LinkedList<>();

    public void viewAccepted(View new_view) {
        System.out.println("** vista: " + new_view);
        System.out.println("--> Formato de aceptación: ");
        System.out.println("--> mesa num, candidato n num, cantidato n+1 num, ...");
    }

    public void receive(Message msg) {
        String line=msg.getSrc() + ": " + msg.getObject();
        System.out.println(line);
        try {
            line = preprocessData(line, msg);
            String[] details = line.split(",");
            for (String detail : details) System.out.println(detail);
            Integer location = Integer.parseInt(details[0]);
            Integer juan_ = Integer.parseInt(details[1]);
            Integer pedro_ = Integer.parseInt(details[2]);
            Integer maria_ = Integer.parseInt(details[3]);
            juan += juan_;
            pedro += pedro_;
            maria += maria_;
            if (locations.containsKey(location)) {
                Integer quantity = locations.get(location);
                quantity += juan_ + pedro_ + maria_;
                locations.put(location, quantity);
            }
            for (HashMap.Entry<Integer, Integer> entry : locations.entrySet()) {
                System.out.println("Resultados Mesa" + entry.getKey().toString() + " = " + entry.getValue().toString() + " votos.");
            }
            System.out.println("Votos Juan: " + juan.toString());
            System.out.println("Votos Pedro: " + pedro.toString());
            System.out.println("Votos Maria: " + maria.toString());
        } catch (Exception ex) {
            System.out.println("Error: No se ha registrado correctamente la votación");
        }
        synchronized(state) {
            state.add(line);
        }
    }
    
    private String preprocessData(String _data, Message _msg) {
        return _data.
                replaceAll(_msg.getSrc() + ":", "").
                replaceAll("\\[", "").replaceAll("\\]", "").
                replaceAll(this.user_name, "").
                replaceAll("mesa", "").
                replaceAll(" ", "").
                replaceAll("[a-zA-Z]", "");
    }

    public void getState(OutputStream output) throws Exception {
        synchronized(state) {
            Util.objectToStream(state, new DataOutputStream(output));
        }
    }

    @SuppressWarnings("unchecked")
    public void setState(InputStream input) throws Exception {
        List<String> list=(List<String>)Util.objectFromStream(new DataInputStream(input));
        synchronized(state) {
            state.clear();
            state.addAll(list);
        }
        System.out.println("estado recibido (" + list.size() + " mensajes en la historia del chat ):");
        for(String str: list) {
            System.out.println(str);
        }
    }


    private void start() throws Exception {
        channel=new JChannel();
        channel.setReceiver(this);
        channel.connect("ChatCluster");
        channel.getState(null, 10000);
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print("> "); System.out.flush();
                String line=in.readLine().toLowerCase();
                if(line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }
                line="[" + user_name + "] " + line;
                Message msg=new Message(null,line);
                channel.send(msg);
            }
            catch(Exception e) {
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.print("Introduzca su nombre: ");
        String nombre;
        nombre=sc.next();
        new CountChat(nombre).start();
    }
}