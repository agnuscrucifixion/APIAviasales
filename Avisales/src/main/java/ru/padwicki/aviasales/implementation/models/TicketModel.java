package ru.padwicki.aviasales.implementation.models;

import lombok.Data;

@Data
public class TicketModel {
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
