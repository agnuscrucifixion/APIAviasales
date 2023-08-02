package ru.padwicki.aviasales.implementation.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import ru.padwicki.aviasales.api.dto.BuyTicketRqDTO;
import ru.padwicki.aviasales.api.dto.GetTicketByStartRqDTO;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.implementation.controllersInterfaces.UserTicketControllerInterface;
import ru.padwicki.aviasales.implementation.exception.NoExistSuchTickets;
import ru.padwicki.aviasales.implementation.impl.AdminImpl;
import ru.padwicki.aviasales.implementation.impl.UserImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionAdminImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionOfUserImpl;
import ru.padwicki.aviasales.implementation.services.UserService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserTicketController implements UserTicketControllerInterface, InjectionAdminImpl, InjectionOfUserImpl {
    AdminImpl ticket;
    UserImpl user;

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
        user.buyTicket(buyTicketRqDTO.getPrice(), buyTicketRqDTO.getStartTown(),
                buyTicketRqDTO.getEndTown(), buyTicketRqDTO.getDate_time(), buyTicketRqDTO.getNickname());

    }


    @Override
    public List<GetTicketByStartRqDTO> getTicketsByStartTown(@RequestParam String startTown,
                                                             @RequestParam String hours) throws ParseException {
        return user.getTicketsByStartTown(startTown,hours);
    }

    @Override
    public List<TicketDTO> getTicketsByAirport(@RequestParam String airport, @RequestParam String startTime,
                                               @RequestParam String endTime) throws ParseException {
        return user.getTicketsByAirport(airport,startTime,endTime);
    }


}
