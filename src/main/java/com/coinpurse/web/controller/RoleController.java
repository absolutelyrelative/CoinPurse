package com.coinpurse.web.controller;

import com.coinpurse.web.dto.role.RoleDto;
import com.coinpurse.web.mapper.RoleMapper;
import com.coinpurse.web.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {
    private final RoleServices roleServices;

    @Autowired
    public RoleController(RoleServices roleServices) {
        this.roleServices = roleServices;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto updateRole(@RequestBody RoleDto roleDto) {
        return RoleMapper.mapToRoleDto(roleServices.saveRole(
                RoleMapper.mapToRole(roleDto)
        ));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto saveRole(@RequestBody RoleDto roleDto) {
        return RoleMapper.mapToRoleDto(roleServices.saveRole(
                RoleMapper.mapToRole(roleDto)
        ));
    }

    @GetMapping(value = "/{roleId}", produces = "application/json")
    public RoleDto getRole(@PathVariable Long roleId) {
        return RoleMapper.mapToRoleDto(roleServices.getRoleById(roleId));
    }

    @RequestMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> getAllRoles() {
        return roleServices.getAll().stream()
                .map(RoleMapper::mapToRoleDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable Long roleId) {
        roleServices.deleteRole(roleServices.getRoleById(roleId));
    }
}
