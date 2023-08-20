package ru.padwicki.tire.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private Integer ticketsBuy;
}
