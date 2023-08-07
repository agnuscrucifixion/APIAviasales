package ru.padwicki.aviasales.implementation.injection;

import ru.padwicki.brokers.rabbitmq.ProducerRabbit;

public interface InjectionProducer {
    public void setProducer(ProducerRabbit producerRabbit);
}
