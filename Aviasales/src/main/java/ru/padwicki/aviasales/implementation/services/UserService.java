package ru.padwicki.aviasales.implementation.services;


import org.springframework.web.bind.annotation.RequestParam;
import ru.padwicki.tire.dto.GetTicketByStartRqDTO;
import ru.padwicki.tire.dto.TicketDTO;

import java.util.List;


public interface UserService {

    public void buyTicket(@RequestParam Integer price, @RequestParam String startTown, @RequestParam String endTown,
                          @RequestParam String date_time, @RequestParam String nickname);



    public List<GetTicketByStartRqDTO> getTicketsByStartTown(@RequestParam String startTown,
                                                             @RequestParam String hours);

    public List<TicketDTO> getTicketsByAirport(@RequestParam String airport,
                                               @RequestParam String startTime,
                                               @RequestParam String endTime);
}
