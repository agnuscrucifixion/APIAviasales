package ru.padwicki.brokers.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(String msg) {

    }
//    @RabbitListener(queues = "email")
//    public void sendEMail(String msg) {
//        System.out.println("Recieved Message: " + msg);
//    }
}
