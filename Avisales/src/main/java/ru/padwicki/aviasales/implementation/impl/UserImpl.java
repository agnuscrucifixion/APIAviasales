package ru.padwicki.aviasales.implementation.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.padwicki.aviasales.api.dto.GetTicketByStartRqDTO;
import ru.padwicki.aviasales.api.dto.TicketDTO;
import ru.padwicki.aviasales.domain.entity.Ticket;
import ru.padwicki.aviasales.domain.entity.User;
import ru.padwicki.aviasales.domain.repository.TicketRepository;
import ru.padwicki.aviasales.domain.repository.UserRepository;
import ru.padwicki.aviasales.implementation.exception.NoExistSuchTickets;
import ru.padwicki.aviasales.implementation.injection.InjectionAdminImpl;
import ru.padwicki.aviasales.implementation.injection.InjectionOfTicketMapper;
import ru.padwicki.aviasales.implementation.mapper.TicketMapperImpl;
import ru.padwicki.aviasales.implementation.services.UserService;

import java.util.*;

@Service
public class UserImpl implements UserService, InjectionOfTicketMapper, InjectionAdminImpl {

    private final TicketRepository ticketRepo;
    private final UserRepository userRepository;
    TicketMapperImpl ticketMapper;
    AdminImpl ticket;

    @Autowired
    public UserImpl(TicketRepository ticketRepo, UserRepository userRepository) {
        this.ticketRepo = ticketRepo;
        this.userRepository = userRepository;
    }

    @Autowired
    @Override
    public void setAdminImpl(AdminImpl adminImpl) {
        ticket = adminImpl;
    }
    @Autowired
    @Override
    public void setTicketMapperImpl(TicketMapperImpl ticketMapper) {
        this.ticketMapper = ticketMapper;
    }
    @Override
    public void buyTicket(Integer price, String startTown, String endTown, String date_time, String nickname) {
        Optional<Ticket> ticketFind = ticketRepo.findByPriceAndStartTownAndEndTownAndDateTime(price,startTown,endTown, date_time);
        if (ticketFind.isPresent()) {
            Ticket ticket = ticketFind.get();
            this.ticket.minusTicketCount(ticket.getId());
            Optional<User> userFind = userRepository.findByNickname(nickname);
            if (userFind.isPresent()) {
                User user = userFind.get();
                user.setTicketsBuy(user.getTicketsBuy() + 1);
                userRepository.save(user);
            } else {
                throw new NoExistSuchTickets();
            }
        } else {
            throw new NoExistSuchTickets();
        }

    }



    @Override
    public List<GetTicketByStartRqDTO> getTicketsByStartTown(String startTown, String hours) {
        List<GetTicketByStartRqDTO> list = new ArrayList<>();
        GregorianCalendar calendarFuture = new GregorianCalendar();
        GregorianCalendar now = new GregorianCalendar();
        calendarFuture.roll(Calendar.HOUR, Integer.parseInt(hours));
        List<Ticket> ticketList = ticketRepo.findAllByStartTown(startTown);
        List<Ticket> finalTicketList = new ArrayList<>();
        for (Ticket value : ticketList) {
            GregorianCalendar calendar1 = new GregorianCalendar();
            String date = value.getDateTime();
            date = date.replace('-', ' ');
            date = date.replace(':', ' ');
            String[] partsOfDate = date.split(" ");
            calendar1.set(Integer.parseInt(partsOfDate[0]), Integer.parseInt(partsOfDate[1]) - 1, Integer.parseInt(partsOfDate[2]),
                    Integer.parseInt(partsOfDate[3]), Integer.parseInt(partsOfDate[4]));


            if (now.before(calendar1) && calendar1.before(calendarFuture)) {
                finalTicketList.add(value);
            }

        }

        if (finalTicketList.isEmpty()) {
            throw new NoExistSuchTickets();
        }
        for (Ticket ticket : finalTicketList) {
            GetTicketByStartRqDTO getTicketByStartRqDTO = new GetTicketByStartRqDTO();
            getTicketByStartRqDTO.ticket = ticketMapper.toDTO(ticket);
            getTicketByStartRqDTO.arrivalPoint = ticket.getEndTown();
            list.add(getTicketByStartRqDTO);
        }
        return list;
    }

    @Override
    public List<TicketDTO> getTicketsByAirport(String airport, String startTime, String endTime) {
        List<Ticket> ticketList = ticketRepo.findAllByAirport(airport);
        List<Ticket> finalTicketList = new ArrayList<>();
        startTime = startTime.replace('-',' ');
        startTime = startTime.replace(':',' ');
        endTime = endTime.replace('-',' ');
        endTime = endTime.replace(':',' ');
        String[] partsOfFirstDate = startTime.split(" ");
        String[] partsOfEndDate = endTime.split(" ");
        GregorianCalendar first = new GregorianCalendar(Integer.parseInt(partsOfFirstDate[0]),
                Integer.parseInt(partsOfFirstDate[1]) - 1, Integer.parseInt(partsOfFirstDate[2]),
                Integer.parseInt(partsOfFirstDate[3]), Integer.parseInt(partsOfFirstDate[4]));
        GregorianCalendar end = new GregorianCalendar(Integer.parseInt(partsOfEndDate[0]),
                Integer.parseInt(partsOfEndDate[1]) - 1, Integer.parseInt(partsOfEndDate[2]),
                Integer.parseInt(partsOfEndDate[3]), Integer.parseInt(partsOfEndDate[4]));
        for (Ticket ticket : ticketList) {
            GregorianCalendar calendar1 = new GregorianCalendar();

            String date = ticket.getDateTime();
            date = date.replace('-', ' ');
            date = date.replace(':', ' ');
            String[] partsOfDate = date.split(" ");
            calendar1.set(Integer.parseInt(partsOfDate[0]), Integer.parseInt(partsOfDate[1]) - 1, Integer.parseInt(partsOfDate[2]),
                    Integer.parseInt(partsOfDate[3]), Integer.parseInt(partsOfDate[4]));


            if (first.before(calendar1) && calendar1.before(end)) {
                finalTicketList.add(ticket);
            }
            if (finalTicketList.isEmpty()) {
                throw new NoExistSuchTickets();
            }
        }
        return ticketMapper.toListDTO(finalTicketList);
    }



}
