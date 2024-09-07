package com.coinpurse.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurseDto {
    private Long id;
    private String title;
    private LocalDateTime creation;
    private Float amount;
    private String currency;
}