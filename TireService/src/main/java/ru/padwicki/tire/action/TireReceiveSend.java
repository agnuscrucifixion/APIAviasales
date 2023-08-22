package ru.padwicki.tire.action;

import com.google.gson.Gson;
import com.rabbitmq.tools.json.JSONUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.retrytopic.DestinationTopic;
import org.springframework.stereotype.Component;
//import ru.padwicki.aviasales.api.dto.BuyTicketRqDTO;
import ru.padwicki.tire.dto.BuyTicketRqDTO;
import ru.padwicki.tire.dto.GetTicketByStartRqDTO;
import ru.padwicki.tire.implementation.injection.InjectionOfKafkaImpl;

import java.util.List;
import java.util.Properties;

@Component
public class TireReceiveSend implements InjectionOfKafkaImpl {

    int idMessage = 0;
    int idBuyTicket = 0;
    int idGetTicketByTown = 0;
    KafkaTemplate<String, String> kafkaTemplateMessage;
    KafkaTemplate<String, BuyTicketRqDTO> kafkaTemplateBuyTicket;
    KafkaTemplate<String, List<GetTicketByStartRqDTO>> kafkaTemplateGetTicketByTown;


    @Override
    @Autowired
    public void setKafkaTemplateMessage(KafkaTemplate<String, String> kafkaTemplateMessage) {
        this.kafkaTemplateMessage = kafkaTemplateMessage;
    }

    @Override
    @Autowired
    public void setKafkaTemplateBuyTicket(KafkaTemplate<String, BuyTicketRqDTO> kafkaTemplateBuyTicket) {
        this.kafkaTemplateBuyTicket = kafkaTemplateBuyTicket;
    }

    @Override
    @Autowired
    public void setKafkaTemplateGetTicketByTown(KafkaTemplate<String, List<GetTicketByStartRqDTO>> kafkaTemplateGetTicketByTown) {
        this.kafkaTemplateGetTicketByTown = kafkaTemplateGetTicketByTown;
    }


    @RabbitListener(queues = "buyTicket")
    public void receiveBuyTicket(BuyTicketRqDTO buyTicketRqDTO) {
        System.out.println("RABBIT RECEIVE MESSAGE : " + buyTicketRqDTO + "\n");
        idBuyTicket++;
        sendBuyTicket(Integer.toString(idBuyTicket), buyTicketRqDTO);
    }

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(String msg) {
        System.out.println("RABBIT RECEIVE MESSAGE : " + msg + "\n");
        idMessage++;
        sendMessage(Integer.toString(idMessage), msg);
    }
    @RabbitListener(queues = "getTicketByTown")
    public void receiveGetTicketByTown(List<GetTicketByStartRqDTO> getTicketByStartRqDTOList) {
        System.out.println("RABBIT RECEIVE MESSAGE : " + getTicketByStartRqDTOList + "\n");
        idGetTicketByTown++;
        sendGetTicketByTown(Integer.toString(idGetTicketByTown), getTicketByStartRqDTOList);
    }

    public void sendMessage(String msgId, String msg) {
        System.out.println("KAFKA SEND " + msg);
        kafkaTemplateMessage.send("msg", msgId, msg);
    }

    public void sendBuyTicket(String msgId, BuyTicketRqDTO msg) {
        System.out.println("KAFKA SEND " + msg);
        kafkaTemplateBuyTicket.send("buyTicket", msgId, msg);
    }
    public void sendGetTicketByTown(String msgId, List<GetTicketByStartRqDTO> msg) {
        System.out.println("KAFKA SEND " + msg);
        kafkaTemplateGetTicketByTown.send("getTicketsByTown",msgId,msg);
    }

}
