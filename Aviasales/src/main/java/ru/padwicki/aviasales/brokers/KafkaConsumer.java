package ru.padwicki.aviasales.brokers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.padwicki.aviasales.api.dto.BuyTicketRqDTO;

@Component
public class KafkaConsumer {
    @KafkaListener(topics="msg")
    public void msgListener(String msg){
        System.out.println("Kafka LISTEN MESSAGE " + msg);
    }

    @KafkaListener(topics = "buyTicket")
    public void ticketListener(BuyTicketRqDTO buyTicketRqDTO) {
        System.out.println("Kafka LISTEN MESSAGE " + buyTicketRqDTO);
    }
}
