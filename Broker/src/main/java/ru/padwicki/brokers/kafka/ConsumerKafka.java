package ru.padwicki.brokers.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerKafka {
    @KafkaListener(topics="msg")
    public void msgListener(String msg){
        System.out.println("Kafka LISTEN MESSAGE" + msg);
    }
}
