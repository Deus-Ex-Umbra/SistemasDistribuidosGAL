package ejercicio.n.pkg1;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import java.nio.charset.StandardCharsets;

public class Recv {
    private final static String QUEUE_NAME = "operations";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Esperando... Para salir presione (ctrl + c)");
            DeliverCallback deliver_callback = (consumer_tag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [*] Recibido: " + message + "");
                String[] operation = message.split(" ");
                Float num1 = Float.parseFloat(operation[0]);
                String operator = operation[1];
                Float num2 = Float.parseFloat(operation[2]);
                Float result;
                Operator.setNumbers(num1, num2);
                switch (operator) {
                    case "+":
                        result = Operator.add();
                        break;
                    case "-":
                        result = Operator.substract();
                        break;
                    case "*":
                        result = Operator.multiply();
                        break;
                    case "/":
                        result = Operator.divide();
                        break;
                    default:
                        result = null;
                        break;
                }
                String str_result = (result == null) ? "Entradas no válidas" : result.toString();
            };
            channel.basicConsume(QUEUE_NAME, true, deliver_callback, consumer_tag -> {});
        }
    }
}
