package co.nilin.mvc.tst;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import static java.lang.Thread.*;
import static java.lang.Thread.sleep;

public class Send {

    private final static String QUEUE_NAME = "hello";
    static int num=0;
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "The Message"+num;

            Thread t1 = new Thread(() -> {
                try {
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));

                } catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    System.out.println(" [x] Sent '" + message + "'");
                }
            });

            t1.start();
            t1.sleep(1000);


        }
    }
}