package com.coinpurse.web.controller;

import com.coinpurse.web.services.CurrencyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/currencies")
public class CurrencyController {

    private final CurrencyServices currencyServices;

    @Autowired
    public CurrencyController(CurrencyServices currencyServices) {
        this.currencyServices = currencyServices;
    }

    @PostMapping(value = "/refresh")
    public void refreshCurrencies() {
        currencyServices.getAllCurrencies(LocalDate.now());
    }

}
