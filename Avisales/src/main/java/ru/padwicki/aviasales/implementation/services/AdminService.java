package ru.padwicki.aviasales.implementation.services;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.api.dto.UserDTO;
import ru.padwicki.aviasales.domain.entity.Ticket;
import ru.padwicki.aviasales.domain.entity.User;
import ru.padwicki.aviasales.implementation.exception.NoExistSuchTickets;

import java.util.List;


public interface AdminService {
    public List<TicketDTO> getTickets();

    public Ticket addTicket(@RequestBody TicketDTO newTicket);

    public void updateStartTown(@RequestParam Long id, @RequestParam String newStartTown);

    public void updateEndTown(@RequestParam Long id, @RequestParam String newEndTown);

    public void updateAirport(@RequestParam Long id, @RequestParam String newAirport);

    public User addUser(@RequestBody UserDTO newUser);

    public List<UserDTO> getUsers();

}
