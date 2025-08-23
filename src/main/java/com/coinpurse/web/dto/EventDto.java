package com.coinpurse.web.dto;

import com.coinpurse.web.model.Purse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") //For the HTML form
    private LocalDateTime date;
    private LocalDateTime createdon; //TODO: This is generated for now
    private LocalDateTime updatedon; //TODO: This is generated for now
    private String type; //TODO: Change to enum?
    private Float delta;
    private Float finalvalue;
    private Purse purse;
}
