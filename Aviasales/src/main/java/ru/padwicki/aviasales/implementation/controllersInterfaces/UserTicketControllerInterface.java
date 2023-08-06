package ru.padwicki.aviasales.implementation.controllersInterfaces;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import ru.padwicki.aviasales.api.dto.BuyTicketRqDTO;
import ru.padwicki.aviasales.api.dto.GetTicketByStartRqDTO;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.implementation.exception.NoExistSuchTickets;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
public interface UserTicketControllerInterface {
    @PostMapping("/buy-ticket")
    public void buyTicket(@RequestBody BuyTicketRqDTO buyTicketRqDTO);


    @GetMapping("/get-tickets-by-startTown")
    public List<GetTicketByStartRqDTO> getTicketsByStartTown(@RequestParam String startTown,
                                                             @RequestParam String hours) throws ParseException;
    @GetMapping("/get-tickets-by-airport")
    public List<TicketDTO> getTicketsByAirport(@RequestParam String airport,
                                               @RequestParam String startTime,
                                               @RequestParam String endTime) throws ParseException;
}
