package ru.padwicki.aviasales.api.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private Integer ticketsBuy;
}
