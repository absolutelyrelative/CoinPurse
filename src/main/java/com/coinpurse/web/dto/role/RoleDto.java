package com.coinpurse.web.dto.role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RoleDto {
    private Long id;
    private String name;
}
