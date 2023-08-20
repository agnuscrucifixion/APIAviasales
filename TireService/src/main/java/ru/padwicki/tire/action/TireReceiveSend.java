package ru.padwicki.tire.action;

import com.google.gson.Gson;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.retrytopic.DestinationTopic;
import org.springframework.stereotype.Component;
//import ru.padwicki.aviasales.api.dto.BuyTicketRqDTO;
import ru.padwicki.tire.dto.BuyTicketRqDTO;
import ru.padwicki.tire.implementation.injection.InjectionOfKafkaImpl;

import java.util.Properties;

@Component
public class TireReceiveSend implements InjectionOfKafkaImpl {

    int idMessage = 0;
    int idBuyTicket = 0;
    KafkaTemplate<String, String> kafkaTemplateMessage;
    KafkaTemplate<String, BuyTicketRqDTO> kafkaTemplateBuyTicket;


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



    @RabbitListener(queues = "buyTicket")
    public void receiveBuyTicket(BuyTicketRqDTO buyTicketRqDTO) {
        System.out.println("Tire-Service RABBIT RECEIVE MESSAGE : " + buyTicketRqDTO + "\n");
        idBuyTicket++;
        sendBuyTicket(Integer.toString(idBuyTicket), buyTicketRqDTO);
    }

    //почему-то не слушает очередь
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(String msg) {
        System.out.println("Tire-Service RABBIT RECEIVE MESSAGE : " + msg + "\n");
        idMessage++;
        sendMessage(Integer.toString(idMessage), msg);
    }

    public void sendMessage(String msgId, String msg) {
        System.out.println("Tire-Service KAFKA SEND " + msg);
        kafkaTemplateMessage.send("msg", msgId, msg);
    }

    public void sendBuyTicket(String msgId, BuyTicketRqDTO msg) {
        System.out.println("Tire-Service KAFKA SEND " + msg);
        kafkaTemplateBuyTicket.send("buyTicket", msgId, msg);
    }

}
