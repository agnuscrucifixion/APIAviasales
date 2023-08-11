package ru.padwicki.aviasales.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.padwicki.aviasales.implementation.injection.InjectionKafkaListener;
import ru.padwicki.brokers.kafka.ConsumerKafka;

@Component
public class KafkaListener implements InjectionKafkaListener {

    ConsumerKafka consumerKafka;
    @Autowired
    @Override
    public void setConsumerKafka(ConsumerKafka consumerKafka) {
        this.consumerKafka = consumerKafka;
    }

    public void kafkaListen(String msg) {
        consumerKafka.msgListener(msg);
    }
}
