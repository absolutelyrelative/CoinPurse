package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.currency.CurrencyDto;
import com.coinpurse.web.model.Currency;

public class CurrencyMapper {
    public static CurrencyDto toCurrencyDto(Currency currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .currencyDescription(currency.getCurrencyDescription())
                .currency(currency.getCurrency())
                .updatedOn(currency.getUpdatedon())
                .build();
    }
}
