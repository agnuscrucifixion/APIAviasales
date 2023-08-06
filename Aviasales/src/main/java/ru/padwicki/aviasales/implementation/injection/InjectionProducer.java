package ru.padwicki.aviasales.implementation.injection;

import ru.padwicki.brokers.rabbitmq.Producer;

public interface InjectionProducer {
    public void setProducer(Producer producer);
}
