package ru.padwicki.tire.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Data
public class BuyTicketRqDTO implements Serializable {
    @JsonProperty
    Integer price;
    @JsonProperty
    String startTown;
    @JsonProperty
    String endTown;
    @JsonProperty
    String date_time;
    @JsonProperty
    String nickname;
}
