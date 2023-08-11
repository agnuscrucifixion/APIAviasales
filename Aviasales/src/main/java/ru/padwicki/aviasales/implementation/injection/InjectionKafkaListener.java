package ru.padwicki.aviasales.implementation.injection;

import ru.padwicki.brokers.kafka.ConsumerKafka;

public interface InjectionKafkaListener {
    public void setConsumerKafka(ConsumerKafka consumerKafka);
}
