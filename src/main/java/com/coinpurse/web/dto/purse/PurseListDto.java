package com.coinpurse.web.dto.purse;

import com.coinpurse.web.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurseListDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creation;
    private String currency;
    private UserEntity createdBy;
}
