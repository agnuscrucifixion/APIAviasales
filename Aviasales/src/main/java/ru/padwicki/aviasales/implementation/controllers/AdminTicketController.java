package ru.padwicki.aviasales.implementation.controllers;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.api.dto.UserDTO;
import ru.padwicki.aviasales.domain.entity.Ticket;
import ru.padwicki.aviasales.domain.entity.User;
import ru.padwicki.aviasales.implementation.controllersInterfaces.AdminTicketControllerInterface;
import ru.padwicki.aviasales.implementation.exception.NoExistSuchTickets;
import ru.padwicki.aviasales.implementation.impl.AdminImpl;
import ru.padwicki.aviasales.implementation.impl.UserImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionAdminImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionProducer;
import ru.padwicki.brokers.rabbitmq.Producer;

import java.util.List;

@RestController

public class AdminTicketController implements AdminTicketControllerInterface, InjectionAdminImpl, InjectionProducer {

    AdminImpl ticket;
    Producer producer;

    @Autowired
    @Override
    public void setProducer(Producer producer) {
        this.producer = producer;
    }
    @Autowired
    @Override
    public void setAdminImpl(AdminImpl adminImpl) {
        ticket = adminImpl;
    }


    public List<TicketDTO> getTickets() {
        return ticket.getTickets();
    }

    public Ticket addTicket(@RequestBody TicketDTO newTicket) {
//        String messageString = ;
//        Message message = new Message(messageString.getBytes());
//        for (int i = 0; i < 500; i++) {
            producer.produceMsg("New TICKETS TO " + newTicket.getEndTown() + " COME!");

        return ticket.addTicket(newTicket);
    }

    public void updateStartTown(@RequestParam Long id, @RequestParam String newStartTown) {
        ticket.updateStartTown(id,newStartTown);
    }

    public void updateEndTown(@RequestParam Long id, @RequestParam String newEndTown) {
        ticket.updateEndTown(id, newEndTown);
    }

    public void updateAirport(@RequestParam Long id, @RequestParam String newAirport) {
        ticket.updateAirport(id,newAirport);
    }

    public User addUser(@RequestBody UserDTO newUser) {
        return ticket.addUser(newUser);
    }

    public List<UserDTO> getUsers() {
        return ticket.getUsers();
    }


}
