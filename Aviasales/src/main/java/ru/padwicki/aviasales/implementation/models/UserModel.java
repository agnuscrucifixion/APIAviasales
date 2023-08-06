package ru.padwicki.aviasales.implementation.models;

import lombok.Data;

@Data
public class UserModel {
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private Integer ticketsBuy;
}
