package com.tanlaplatforms.hrishikeshpatil;

import java.nio.charset.StandardCharsets;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("user");
        factory.setPassword("Welcome@123");
        try (Connection connection = factory.newConnection();) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("random-stream", false, false, false, null);
            channel.basicConsume("random-stream", true, (_consumerTag, message) -> {
                System.out.println("Received: " + new String(message.getBody(), StandardCharsets.UTF_8));
            }, _consumerTag -> {

            });
            while (true) {
                int number = (int) Math.round(Math.random() * 10000);
                String message = "New random number: " + number;
                channel.basicPublish("", "random-stream", null, message.getBytes());
                System.out.println("Published message with random number: " + number);
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}