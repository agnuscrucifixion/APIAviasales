package ru.padwicki.tire.implementation.injection;

import org.springframework.kafka.core.KafkaTemplate;
//import ru.padwicki.aviasales.api.dto.BuyTicketRqDTO;

public interface InjectionOfKafkaImpl {
    public void setKafkaTemplateMessage(KafkaTemplate<String, String> kafkaTemplateMessage);
//    public void  setKafkaTemplateBuyTicket(KafkaTemplate<String, > kafkaTemplateBuyTicket);
}
