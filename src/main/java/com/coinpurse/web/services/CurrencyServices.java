package com.coinpurse.web.services;

import com.coinpurse.web.dto.currency.CurrencyDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CurrencyServices {
    public Map<String, String> refreshCurrencies(LocalDate localDate);
}
