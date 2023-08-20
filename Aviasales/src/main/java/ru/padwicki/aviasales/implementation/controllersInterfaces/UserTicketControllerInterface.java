package ru.padwicki.aviasales.implementation.controllersInterfaces;

import org.springframework.web.bind.annotation.*;
import ru.padwicki.tire.dto.BuyTicketRqDTO;
import ru.padwicki.tire.dto.GetTicketByStartRqDTO;
import ru.padwicki.tire.dto.TicketDTO;

import java.text.ParseException;
import java.util.List;

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
