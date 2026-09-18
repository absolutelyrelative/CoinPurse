package com.coinpurse.web.controller;

import com.coinpurse.web.dto.role.RoleDto;
import com.coinpurse.web.mapper.RoleMapper;
import com.coinpurse.web.services.RoleServices;
import com.coinpurse.web.validation.OnCreate;
import com.coinpurse.web.validation.OnDelete;
import com.coinpurse.web.validation.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/roles")
@Validated
public class RoleController {
    private final RoleServices roleServices;

    @Autowired
    public RoleController(RoleServices roleServices) {
        this.roleServices = roleServices;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto updateRole(@RequestBody @Validated(OnUpdate.class) RoleDto roleDto) {
        return RoleMapper.mapToRoleDto(roleServices.saveRole(
                RoleMapper.mapToRole(roleDto)
        ));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto saveRole(@RequestBody @Validated(OnCreate.class) RoleDto roleDto) {
        return RoleMapper.mapToRoleDto(roleServices.saveRole(
                RoleMapper.mapToRole(roleDto)
        ));
    }

    @GetMapping(value = "/{roleId}", produces = "application/json")
    public RoleDto getRole(@PathVariable @NotNull @Min(1) Long roleId) {
        return RoleMapper.mapToRoleDto(roleServices.getRoleById(roleId));
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> getAllRoles() {
        return roleServices.getAll().stream()
                .map(RoleMapper::mapToRoleDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{roleId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable @NotNull @Min(1) Long roleId) {
        roleServices.deleteRole(roleServices.getRoleById(roleId));
    }
}
