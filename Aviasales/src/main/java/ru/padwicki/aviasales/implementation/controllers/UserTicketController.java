package ru.padwicki.aviasales.implementation.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.padwicki.tire.dto.BuyTicketRqDTO;
import ru.padwicki.tire.dto.GetTicketByStartRqDTO;
import ru.padwicki.tire.dto.TicketDTO;
import ru.padwicki.aviasales.brokers.ProducerRabbit;
import ru.padwicki.aviasales.implementation.controllersInterfaces.UserTicketControllerInterface;
import ru.padwicki.aviasales.implementation.impl.AdminImpl;
import ru.padwicki.aviasales.implementation.impl.UserImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionAdminImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionOfUserImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionProducer;

import java.util.List;

@RestController
public class UserTicketController implements UserTicketControllerInterface, InjectionAdminImpl, InjectionOfUserImpl, InjectionProducer {
    AdminImpl ticket;
    UserImpl user;

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

    @Autowired
    @Override
    public void setUserImpl(UserImpl userImpl) {
        user = userImpl;
    }

    @Override
    public void buyTicket(@RequestBody @NotNull BuyTicketRqDTO buyTicketRqDTO) {
        producerRabbit.produceTicket(buyTicketRqDTO);
        user.buyTicket(buyTicketRqDTO.getPrice(), buyTicketRqDTO.getStartTown(),
                buyTicketRqDTO.getEndTown(), buyTicketRqDTO.getDate_time(), buyTicketRqDTO.getNickname());

    }


    @Override
    public List<GetTicketByStartRqDTO> getTicketsByStartTown(@RequestParam String startTown,
                                                             @RequestParam String hours) {
        List<GetTicketByStartRqDTO> listOfTickets = user.getTicketsByStartTown(startTown,hours);
        producerRabbit.produceGetTicket(listOfTickets);
        return listOfTickets;
    }

    @Override
    public List<TicketDTO> getTicketsByAirport(@RequestParam String airport, @RequestParam String startTime,
                                               @RequestParam String endTime) {
        return user.getTicketsByAirport(airport,startTime,endTime);
    }



}
