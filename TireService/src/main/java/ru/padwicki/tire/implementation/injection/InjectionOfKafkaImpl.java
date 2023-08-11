package ru.padwicki.tire.implementation.injection;

import org.springframework.kafka.core.KafkaTemplate;

public interface InjectionOfKafkaImpl {
    public void setKafkaTemplate(KafkaTemplate<String, String> kafkaTemplate);
}
