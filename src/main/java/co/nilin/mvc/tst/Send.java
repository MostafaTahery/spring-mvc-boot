package co.nilin.mvc.tst;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class Send implements Runnable {

    private final static String QUEUE_NAME = "hello";
    static int num=0;

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        AtomicReference<String> message = new AtomicReference<>();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

                    } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}