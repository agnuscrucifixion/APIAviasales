package ru.padwicki.aviasales.implementation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.api.dto.UserDTO;
import ru.padwicki.aviasales.domain.entity.Ticket;
import ru.padwicki.aviasales.domain.entity.User;
import ru.padwicki.aviasales.domain.repository.TicketRepository;
import ru.padwicki.aviasales.domain.repository.UserRepository;
import ru.padwicki.aviasales.implementation.exception.NoExistSuchTickets;
import ru.padwicki.aviasales.implementation.injection.InjectionOfTicketMapper;
import ru.padwicki.aviasales.implementation.injection.InjectionOfUserMapper;
import ru.padwicki.aviasales.implementation.mapper.TicketMapperImpl;
import ru.padwicki.aviasales.implementation.mapper.UserMapperImpl;
import ru.padwicki.aviasales.implementation.services.AdminService;

import java.util.List;
import java.util.Optional;

@Service
public class AdminImpl implements AdminService, InjectionOfTicketMapper, InjectionOfUserMapper {


    private final TicketRepository ticketRepo;
    private final UserRepository userRepository;
    TicketMapperImpl ticketMapper;
    UserMapperImpl userMapper;

    @Autowired
    public AdminImpl(TicketRepository ticketRepo, UserRepository userRepository) {
        this.ticketRepo = ticketRepo;
        this.userRepository = userRepository;
    }


    @Autowired
    @Override
    public void setTicketMapperImpl(TicketMapperImpl ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    @Autowired
    @Override
    public void setUserMapperImpl(UserMapperImpl userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public List<TicketDTO> getTickets() {
        List<TicketDTO> list = ticketMapper.toListDTO(ticketRepo.findAll());
        if (list.isEmpty()) {
            throw new NoExistSuchTickets();
        }
        return list;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> list = userMapper.toListDTO(userRepository.findAll());
        if (list.isEmpty()) {
            throw new NoExistSuchTickets();
        }
        return list;
    }
    @Override
    public Ticket addTicket(TicketDTO newTicket) {
        return ticketRepo.save(ticketMapper.toEntity(newTicket));
    }

    @Override
    public void updateStartTown(Long id, String newStartTown) {
        Optional<Ticket> optionalTicket = ticketRepo.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setStartTown(newStartTown);
            ticketRepo.save(ticket);
        } else {
            throw new NoExistSuchTickets();
        }
    }

    @Override
    public void updateEndTown(Long id, String newEndTown) {
        Optional<Ticket> optionalTicket = ticketRepo.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setEndTown(newEndTown);
            ticketRepo.save(ticket);
        } else {
            throw new NoExistSuchTickets();
        }
    }

    @Override
    public void updateAirport(Long id, String newAirport) {
        Optional<Ticket> optionalTicket = ticketRepo.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setAirport(newAirport);
            ticketRepo.save(ticket);
        } else {
            throw new NoExistSuchTickets();
        }
    }

    @Override
    public User addUser(UserDTO newUser) {
        return userRepository.save(userMapper.toEntity(newUser));
    }



    public void minusTicketCount(Long id) {
        Optional<Ticket> ticketDTO = ticketRepo.findById(id);
        if (ticketDTO.isPresent()) {
            Ticket ticket = ticketDTO.get();
            if (ticket.getRemaining() != 0) {
                ticket.setRemaining(ticket.getRemaining() - 1);
                ticketRepo.save(ticket);
            } else {
                throw new NoExistSuchTickets();
            }
        }
    }


}
