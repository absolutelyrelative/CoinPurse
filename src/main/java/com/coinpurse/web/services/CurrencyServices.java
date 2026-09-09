package com.coinpurse.web.services;

import com.coinpurse.web.model.Currency;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CurrencyServices {
    public Mono<Map<String, String>> refreshCurrencies(LocalDate localDate);

    public List<Currency> getAllCurrencies();
}
