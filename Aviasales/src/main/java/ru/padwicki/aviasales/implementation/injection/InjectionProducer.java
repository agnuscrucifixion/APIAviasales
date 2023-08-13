package ru.padwicki.aviasales.implementation.injection;

import ru.padwicki.aviasales.brokers.ProducerRabbit;

public interface InjectionProducer {
    public void setProducer(ProducerRabbit producerRabbit);
}
