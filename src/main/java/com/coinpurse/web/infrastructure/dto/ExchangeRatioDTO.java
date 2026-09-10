package com.coinpurse.web.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class ExchangeRatioDTO {
    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("eur")
    private Map<String, BigDecimal> ratio;
}
