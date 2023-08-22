package ru.padwicki.aviasales.brokers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.padwicki.tire.dto.BuyTicketRqDTO;
import ru.padwicki.tire.dto.GetTicketByStartRqDTO;

import java.util.List;

@Component
public class KafkaConsumer {
    @KafkaListener(topics="msg", groupId = "1")
    public void msgListener(String msg){
        System.out.println("Kafka LISTEN MESSAGE " + msg);
    }

    @KafkaListener(topics = "buyTicket", groupId = "1",containerFactory = "buyTicketDtoListener")
    public void buyTicketListener(BuyTicketRqDTO buyTicketRqDTO) {
        System.out.println("Kafka LISTEN MESSAGE " + buyTicketRqDTO);
    }

    @KafkaListener(topics = "getTicketsByTown",groupId = "1",containerFactory = "getTicketByTownListener")
    public void getTicketListener(List<GetTicketByStartRqDTO> getTicketByStartRqDTO) {
        System.out.println("Kafka LISTEN MESSAGE " + getTicketByStartRqDTO);
    }
}
