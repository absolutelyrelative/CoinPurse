package com.coinpurse.web.dto;

import com.coinpurse.web.model.UserEntity;
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
    private String currency;
    private UserEntity createdBy;
    private List<EventDto> events;
}