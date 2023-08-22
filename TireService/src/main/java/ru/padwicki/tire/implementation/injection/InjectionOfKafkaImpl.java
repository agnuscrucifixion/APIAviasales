package ru.padwicki.tire.implementation.injection;
import org.springframework.kafka.core.KafkaTemplate;
import ru.padwicki.tire.dto.BuyTicketRqDTO;
import ru.padwicki.tire.dto.GetTicketByStartRqDTO;

import java.util.List;


public interface InjectionOfKafkaImpl {
    public void setKafkaTemplateMessage(KafkaTemplate<String, String> kafkaTemplateMessage);
    public void  setKafkaTemplateBuyTicket(KafkaTemplate<String, BuyTicketRqDTO> kafkaTemplateBuyTicket);

    public void setKafkaTemplateGetTicketByTown(KafkaTemplate<String, List<GetTicketByStartRqDTO>> kafkaTenplateGetTicketByTown);
}
