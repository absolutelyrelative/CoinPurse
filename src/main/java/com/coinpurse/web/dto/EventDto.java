package com.coinpurse.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String comment;
    private LocalDateTime date;
    private LocalDateTime createdon;
    private LocalDateTime updatedon;
    private String type; //TODO: Change to enum?
    private Float delta;
    private Float finalvalue; //TODO: Generate with annotations?
}
