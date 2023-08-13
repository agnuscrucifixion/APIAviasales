//package ru.padwicki.aviasales.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Slf4j
//public class RabbitConfig {
//    private final CachingConnectionFactory cachingConnectionFactory;
//
//    public RabbitConfig(CachingConnectionFactory cachingConnectionFactory) {
//        this.cachingConnectionFactory = cachingConnectionFactory;
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter converter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
//        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
//        template.setMessageConverter(converter);
//        return template;
//    }
//}
