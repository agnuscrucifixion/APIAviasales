package ru.padwicki.aviasales.brokers;


import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.padwicki.tire.dto.BuyTicketRqDTO;
import ru.padwicki.tire.dto.GetTicketByStartRqDTO;

import java.util.List;

@Component
public class ProducerRabbit {

    private RabbitTemplate rabbitTemplate;
    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    private String exchange = "jsa.rabbitmq.direct";
    private String routingKey = "jsa.rabbitmq.routingkey";
    public static final String queue = "jsa.rabbitmq.queue";


    private String exchangeBuyTicket = "exchangeBuyTicket";
    private String routingKeyBuyTicket = "routingKeyBuyTicket";
    private String queueBuyTicket = "buyTicket";


    private String exchangeGetTicketByTown = "exchangeGetTicketByTown";
    private String routingKeyGetTicketByTown = "routingKeyGetTicketByTown";
    private String queueGetTicketByTown = "getTicketByTown";

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
    public Binding bindingBuyTicket(Queue queueBuyTicket, TopicExchange exchangeBuyTicket) {
        return BindingBuilder.bind(queueBuyTicket).to(exchangeBuyTicket).with(routingKeyBuyTicket);
    }


    @Bean
    public Queue queueGetTicketByTown() {
        return new Queue(queueGetTicketByTown, false);
    }
    @Bean
    public TopicExchange exchangeGetTicketByTown() {
        return new TopicExchange(exchangeGetTicketByTown);
    }
    @Bean
    public Binding bindingGetTicketByTown(Queue queueGetTicketByTown, TopicExchange exchangeGetTicketByTown) {
        return BindingBuilder.bind(queueGetTicketByTown).to(exchangeGetTicketByTown).with(routingKeyGetTicketByTown);
    }



    public void produceMsg(String msg) {
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("RABBIT SEND MESSAGE = " + msg);
    }

    public void produceTicket(@NotNull BuyTicketRqDTO buyTicketRqDTO) {
        rabbitTemplate.convertAndSend(exchangeBuyTicket,routingKeyBuyTicket,buyTicketRqDTO);
        System.out.println("RABBIT SEND MESSAGE = " + buyTicketRqDTO);
    }

    public void produceGetTicket(List<GetTicketByStartRqDTO> getTicketByStartRqDTO) {
        rabbitTemplate.convertAndSend(exchangeGetTicketByTown,routingKeyGetTicketByTown, getTicketByStartRqDTO);
        System.out.println("RABBIT SEND MESSAGE = " + getTicketByStartRqDTO);
    }

    public void publishEmail(String msg) {
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("Send msg = " + msg);
    }
}


