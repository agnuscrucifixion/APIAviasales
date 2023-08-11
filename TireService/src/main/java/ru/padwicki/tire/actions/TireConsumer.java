package ru.padwicki.tire.actions;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.padwicki.tire.implementation.injection.InjectionOfKafkaImpl;

@Component
public class TireConsumer implements InjectionOfKafkaImpl {
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void receiveMessage(String msg) {
        System.out.println("Tire-Service RECEIVE MESSAGE : " + msg + "\n");
    }

    public void sendOrder(String msgId, String msg) {
        kafkaTemplate.send("msg",msgId,msg);
    }

}
