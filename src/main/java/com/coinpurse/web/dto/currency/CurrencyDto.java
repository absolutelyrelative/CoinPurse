package com.coinpurse.web.dto.currency;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyDto {
    private Long id;
    private String currency;
    private String currencyDescription;
    private BigDecimal conversionRatioToEur;
    private LocalDateTime updatedOn;
}
