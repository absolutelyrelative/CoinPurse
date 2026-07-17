package com.coinpurse.web.controller;

import com.coinpurse.web.dto.currency.CurrencyDto;
import com.coinpurse.web.mapper.CurrencyMapper;
import com.coinpurse.web.services.CurrencyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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
        currencyServices.refreshCurrencies(LocalDate.now());
    }

    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<List<CurrencyDto>> getCurrencies() {
        List<CurrencyDto> currencies = currencyServices.getAllCurrencies().stream().map(
                currency -> CurrencyMapper.toCurrencyDto(currency)
        ).toList();

        return ResponseEntity.ok(currencies);
    }

}
