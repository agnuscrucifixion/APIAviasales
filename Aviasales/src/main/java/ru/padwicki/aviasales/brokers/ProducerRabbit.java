package ru.padwicki.aviasales.brokers;

import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.padwicki.aviasales.api.dto.BuyTicketRqDTO;

@Component
public class ProducerRabbit {

    private AmqpTemplate amqpTemplate;
    @Autowired
    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.template.default-receive-queue}")
    private String queue;

    private String exchangeBuyTicket = "exchangeBuyTicket";
    private String routingKeyBuyTicket = "routingKeyBuyTicket";
    private String queueBuyTicket = "buyTicket";

    @Bean
    public Queue queue() {
        return new Queue(queue,false);
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public Queue queueBuyTicket() {
        return new Queue(queueBuyTicket, false);
    }
    @Bean
    public TopicExchange exchangeBuyTicket() {
        return new TopicExchange(exchangeBuyTicket);
    }
    @Bean
    public Binding bindingBuyTicket(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyBuyTicket);
    }

    public void produceMsg(String msg) {
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("RABBIT SEND MESSAGE = " + msg);
    }

    public void produceTicket(@NotNull BuyTicketRqDTO buyTicketRqDTO) {
        amqpTemplate.convertAndSend(exchangeBuyTicket,routingKeyBuyTicket,buyTicketRqDTO);
        System.out.println("RABBIT SEND MESSAGE = " + buyTicketRqDTO);
    }

    public void publishEmail(String msg) {
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("Send msg = " + msg);
    }
}


