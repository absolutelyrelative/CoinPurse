package com.coinpurse.web.dto.user;

import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    private String username;
    private String email;
    private String password;
}
