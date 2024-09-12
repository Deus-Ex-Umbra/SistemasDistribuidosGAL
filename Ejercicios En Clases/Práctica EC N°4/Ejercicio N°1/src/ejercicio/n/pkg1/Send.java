package ejercicio.n.pkg1;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Send {
    private final static String QUEUE_NAME = "math_operations";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Random random = new Random();
            String[] operators = {"+", "-", "*", "/"};

            for (int i = 0; i < 500; i++) {
                int num1 = random.nextInt(1000) + 1;
                int num2 = random.nextInt(1000) + 1;
                String operator = operators[random.nextInt(operators.length)];
                String operation = num1 + " " + operator + " " + num2;
                channel.basicPublish("", QUEUE_NAME, null, operation.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + operation + "'");
            }
        }
    }
}
