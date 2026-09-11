package com.coinpurse.web.controller;

import com.coinpurse.web.dto.user.RegistrationDto;
import com.coinpurse.web.dto.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    //@PostMapping(value = "/new", produces = "application/json", consumes = "application/json")
    //public ResponseEntity<UserDto> register(RegistrationDto registrationDto) {

    //}
}
