package com.coinpurse.web.services;

import com.coinpurse.web.dto.currency.CurrencyDto;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyServices {
    public List<CurrencyDto> getAllCurrencies(LocalDate localDate);
}
