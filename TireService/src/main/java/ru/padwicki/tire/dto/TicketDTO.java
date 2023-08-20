package ru.padwicki.tire.dto;

import lombok.Data;

@Data
public class TicketDTO {
    Long id;

    String startTown;

    String endTown;

    String way;

    Integer price;

    String airport;

    String dateTime;

    double time;

    Integer remaining;

}
