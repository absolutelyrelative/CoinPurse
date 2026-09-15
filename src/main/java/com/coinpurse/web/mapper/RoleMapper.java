package com.coinpurse.web.mapper;

import com.coinpurse.web.dto.role.RoleDto;
import com.coinpurse.web.model.Role;

public class RoleMapper {
    public static Role mapToRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());

        return role;
    }

    public static RoleDto mapToRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
