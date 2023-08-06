package ru.padwicki.aviasales.implementation.injection;

import ru.padwicki.aviasales.implementation.mapper.TicketMapper;
import ru.padwicki.aviasales.implementation.mapper.TicketMapperImpl;

public interface InjectionOfTicketMapper {
    public void setTicketMapperImpl(TicketMapperImpl ticketMapper);


}
