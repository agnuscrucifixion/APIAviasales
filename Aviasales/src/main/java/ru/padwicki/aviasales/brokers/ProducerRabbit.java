package ru.padwicki.aviasales.brokers;


import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.padwicki.tire.dto.BuyTicketRqDTO;

@Component
public class ProducerRabbit {

    private RabbitTemplate rabbitTemplate;
    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
//    private AmqpTemplate amqpTemplate;
//    @Autowired
//    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
//        this.amqpTemplate = amqpTemplate;
//    }

//    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange = "jsa.rabbitmq.direct";

//    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey = "jsa.rabbitmq.routingkey";

//    @Value("${spring.rabbitmq.template.default-receive-queue}")
    public static final String queue = "jsa.rabbitmq.queue";

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
    public Binding bindingBuyTicket(Queue queueBuyTicket, TopicExchange exchangeBuyTicket) {
        return BindingBuilder.bind(queueBuyTicket).to(exchangeBuyTicket).with(routingKeyBuyTicket);
    }

    public void produceMsg(String msg) {
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("RABBIT SEND MESSAGE = " + msg);
    }

//    почему-то не отправляет в саму очередь
    public void produceTicket(@NotNull BuyTicketRqDTO buyTicketRqDTO) {
        rabbitTemplate.convertAndSend(exchangeBuyTicket,routingKeyBuyTicket,buyTicketRqDTO);
        System.out.println("RABBIT SEND MESSAGE = " + buyTicketRqDTO);
    }

    public void publishEmail(String msg) {
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
        System.out.println("Send msg = " + msg);
    }
}


