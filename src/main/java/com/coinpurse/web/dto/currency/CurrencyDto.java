package com.coinpurse.web.dto.currency;

import com.coinpurse.web.validation.OnCreate;
import com.coinpurse.web.validation.OnDelete;
import com.coinpurse.web.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.coinpurse.web.constants.ErrorMessages.CURRENCY_NAME_EMPTY;
import static com.coinpurse.web.constants.ErrorMessages.ID_NULL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyDto {
    @NotNull(message=ID_NULL, groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    @NotBlank(message=CURRENCY_NAME_EMPTY, groups = {OnCreate.class, OnUpdate.class})
    private String currency;
    private String currencyDescription;
    private BigDecimal conversionRatioToEur;
    private LocalDateTime updatedOn;
}
