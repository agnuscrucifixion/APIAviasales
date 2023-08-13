package ru.padwicki.tire.actions;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.padwicki.tire.implementation.injection.InjectionOfKafkaImpl;

@Component
public class TireConsumer implements InjectionOfKafkaImpl {
    int id = 0;
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(String msg) {
        System.out.println("Tire-Service RABBIT RECEIVE MESSAGE : " + msg + "\n");
        id++;
        sendOrder(Integer.toString(id),msg);
    }

    public void sendOrder(String msgId, String msg) {
        System.out.println("Tire-Service KAFKA SEND " + msg);
        kafkaTemplate.send("msg",msgId,msg);
    }

}
