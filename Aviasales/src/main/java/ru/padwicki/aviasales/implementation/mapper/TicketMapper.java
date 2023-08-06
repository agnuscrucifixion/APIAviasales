package ru.padwicki.aviasales.implementation.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.domain.entity.Ticket;
import ru.padwicki.aviasales.implementation.models.TicketModel;

import java.util.List;

@Mapper
public interface TicketMapper {

    List<TicketDTO> toListDTO(List<Ticket> tickets);
    List<Ticket> toListTickets(List<TicketDTO> ticketsDTO);
    TicketDTO toDTO(Ticket ticket);
    TicketModel toModel(TicketDTO dto);
    Ticket toEntity(TicketModel model);
    TicketModel toModel(Ticket ticket);
    Ticket toEntity(TicketDTO dto);



}
