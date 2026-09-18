package com.coinpurse.web.dto.role;

import com.coinpurse.web.validation.OnCreate;
import com.coinpurse.web.validation.OnDelete;
import com.coinpurse.web.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.coinpurse.web.constants.ErrorMessages.ID_NULL;
import static com.coinpurse.web.constants.ErrorMessages.ROLE_NAME_EMPTY;

@Builder
@Getter
@Setter
public class RoleDto {
    @NotNull(message = ID_NULL, groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;
    @NotNull(message = ROLE_NAME_EMPTY, groups = {OnCreate.class, OnUpdate.class})
    @NotBlank(message = ROLE_NAME_EMPTY, groups = {OnCreate.class, OnUpdate.class})
    private String name;
}
