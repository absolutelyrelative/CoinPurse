package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.currency.CurrencyDto;
import com.coinpurse.web.model.Currency;

public class CurrencyMapper {
    private CurrencyMapper() {}

    public static CurrencyDto toCurrencyDto(Currency currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .currencyDescription(currency.getCurrencyDescription())
                .currency(currency.getCurrency())
                .conversionRatioToEur(currency.getConversionRatioToEur())
                .updatedOn(currency.getUpdatedon())
                .build();
    }
}
