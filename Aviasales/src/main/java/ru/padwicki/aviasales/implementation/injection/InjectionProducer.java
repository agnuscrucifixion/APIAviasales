package ru.padwicki.aviasales.implementation.injection;

import ru.padwicki.tire.rabbitmq.ProducerRabbit;

public interface InjectionProducer {
    public void setProducer(ProducerRabbit producerRabbit);
}
