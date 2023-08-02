package ru.padwicki.aviasales.implementation.services;


import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.padwicki.aviasales.api.dto.GetTicketByStartRqDTO;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.api.dto.UserDTO;
import ru.padwicki.aviasales.domain.entity.Ticket;
import ru.padwicki.aviasales.implementation.exception.NoExistSuchTickets;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface UserService {

    public void buyTicket(@RequestParam Integer price, @RequestParam String startTown, @RequestParam String endTown,
                          @RequestParam String date_time, @RequestParam String nickname);



    public List<GetTicketByStartRqDTO> getTicketsByStartTown(@RequestParam String startTown,
                                                             @RequestParam String hours);

    public List<TicketDTO> getTicketsByAirport(@RequestParam String airport,
                                               @RequestParam String startTime,
                                               @RequestParam String endTime);
}
