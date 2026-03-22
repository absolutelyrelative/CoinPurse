package com.coinpurse.web.dto;

import com.coinpurse.web.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurseDto {
    private Long id;
    private String title;
    private LocalDateTime creation;
    private String currency;
    private UserEntity createdBy;
    private List<EventDto> events;
}