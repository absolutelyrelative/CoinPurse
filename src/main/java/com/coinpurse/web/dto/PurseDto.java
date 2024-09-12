package com.coinpurse.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PurseDto {
    private Long id;
    //TODO: Figure out why validation dependency is not working
    //@NotEmpty(message = "Purse title should not be empty.")
    private String title;
    private LocalDateTime creation;
    //@NotEmpty(message = "Starting amount should not be empty.")
    private Float amount;
    private String currency;
    private List<EventDto> events;
}