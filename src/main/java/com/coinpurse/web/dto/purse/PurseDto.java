package com.coinpurse.web.dto.purse;

import com.coinpurse.web.dto.event.EventDto;
import com.coinpurse.web.model.User;
import com.coinpurse.web.validation.OnCreate;
import com.coinpurse.web.validation.OnDelete;
import com.coinpurse.web.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.ID_NULL;
import static com.coinpurse.web.constants.ErrorMessages.PURSE_NAME_EMPTY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurseDto {
    @NotNull(message=ID_NULL, groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    @NotNull(message=PURSE_NAME_EMPTY, groups = {OnCreate.class, OnUpdate.class})
    private String title;
    private String description;
    private LocalDateTime creation;
    private String currency; // TODO: Delete, there is a currency entity now :)
    private User createdBy;
    private List<EventDto> events;
}