package ru.padwicki.tire.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Data
public class BuyTicketRqDTO implements Serializable {
    Integer price;
    String startTown;
    String endTown;
    String date_time;
    String nickname;
}
