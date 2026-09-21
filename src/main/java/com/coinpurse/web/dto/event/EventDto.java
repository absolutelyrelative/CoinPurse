package com.coinpurse.web.dto.event;

import com.coinpurse.web.model.Currency;
import com.coinpurse.web.model.Purse;
import com.coinpurse.web.validation.OnCreate;
import com.coinpurse.web.validation.OnDelete;
import com.coinpurse.web.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.coinpurse.web.constants.ErrorMessages.ID_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    @NotNull(message=ID_NULL, groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    private String comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message=ID_NULL, groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime date;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String type; //TODO: Change to enum?
    @NotNull(message=ID_NULL, groups = {OnCreate.class, OnUpdate.class})
    private Float delta;
    private Float finalvalue;
    private Long purseId;
    private Currency currency;

    public EventDto(Long id) {
        this.id = id;
    }
}
