package com.coinpurse.web.services;

import com.coinpurse.web.dto.RegistrationDto;
import com.coinpurse.web.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
