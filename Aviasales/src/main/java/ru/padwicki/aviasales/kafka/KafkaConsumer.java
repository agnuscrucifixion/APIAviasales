package ru.padwicki.aviasales.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics="msg")
    public void msgListener(String msg){
        System.out.println("Kafka LISTEN MESSAGE " + msg);
    }
}
