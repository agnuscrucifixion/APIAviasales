package ru.padwicki.aviasales.implementation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.api.dto.UserDTO;
import ru.padwicki.aviasales.domain.entity.Ticket;
import ru.padwicki.aviasales.domain.entity.User;
import ru.padwicki.aviasales.implementation.controllersInterfaces.AdminTicketControllerInterface;
import ru.padwicki.aviasales.implementation.impl.AdminImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionAdminImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionProducer;
import ru.padwicki.tire.rabbitmq.ProducerRabbit;

import java.util.List;

@RestController

public class AdminTicketController implements AdminTicketControllerInterface, InjectionAdminImpl, InjectionProducer {

    AdminImpl ticket;
    ProducerRabbit producerRabbit;

    @Autowired
    @Override
    public void setProducer(ProducerRabbit producerRabbit) {
        this.producerRabbit = producerRabbit;
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
        producerRabbit.produceMsg(" New TICKETS TO " + newTicket.getEndTown() + " COME!");
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
