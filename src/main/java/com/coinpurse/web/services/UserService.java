package com.coinpurse.web.services;

import com.coinpurse.web.dto.user.RegistrationDto;
import com.coinpurse.web.model.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);

    User findByUsername(String username);
}
