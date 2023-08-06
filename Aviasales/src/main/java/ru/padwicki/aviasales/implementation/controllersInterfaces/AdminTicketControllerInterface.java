package ru.padwicki.aviasales.implementation.controllersInterfaces;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.api.dto.UserDTO;
import ru.padwicki.aviasales.domain.entity.Ticket;
import ru.padwicki.aviasales.domain.entity.User;
import ru.padwicki.aviasales.implementation.exception.NoExistSuchTickets;

import java.util.List;

@RequestMapping("/admin")
public interface AdminTicketControllerInterface {
    @GetMapping("/show-all-ticket")
    public List<TicketDTO> getTickets();

    @PostMapping("/add_ticket")
    public Ticket addTicket(@RequestBody TicketDTO newTicket);

    @PutMapping("/update-by-town-new_start_town")
    public void updateStartTown(@RequestParam Long id, @RequestParam String newStartTown);

    @PutMapping("/update-by-town-new_end_town")
    public void updateEndTown(@RequestParam Long id, @RequestParam String newEndTown);

    @PutMapping("/update-by-town-new_airport")
    public void updateAirport(@RequestParam Long id, @RequestParam String newAirport);

    @PostMapping("/add-user")
    public User addUser(@RequestBody UserDTO newUser);

    @GetMapping("/show-all-users")
    public List<UserDTO> getUsers();
}
