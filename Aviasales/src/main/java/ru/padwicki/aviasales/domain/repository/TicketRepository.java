package ru.padwicki.aviasales.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.padwicki.aviasales.domain.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByPriceAndStartTownAndEndTownAndDateTime(Integer price, String startTown,
                                                                          String endTown, String date_time);

    List<Ticket> findAllByStartTown(String startTown);

    List<Ticket> findAllByAirport(String airport);

}
