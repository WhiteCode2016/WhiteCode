package com.whitecode.rabbitmq.example.helloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者
 * Created by White on 2017/9/27.
 */
public class P {

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);
        String message = "Hello World!";
        channel.basicPublish("","hello",null, message.getBytes("UTF-8"));
        System.out.println("P [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
