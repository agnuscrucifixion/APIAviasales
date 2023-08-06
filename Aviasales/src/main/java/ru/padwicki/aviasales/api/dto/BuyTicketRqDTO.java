package ru.padwicki.aviasales.api.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class BuyTicketRqDTO {
    Integer price;
    String startTown;
    String endTown;
    String date_time;
    String nickname;
}
