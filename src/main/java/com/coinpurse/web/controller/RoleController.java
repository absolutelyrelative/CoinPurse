package com.coinpurse.web.controller;

import com.coinpurse.web.dto.role.RoleDto;
import com.coinpurse.web.mapper.RoleMapper;
import com.coinpurse.web.services.RoleServices;
import com.coinpurse.web.services.impl.RoleServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {
    private final RoleServices roleServices;

    @Autowired
    public RoleController(RoleServices roleServices) {
        this.roleServices = roleServices;
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RoleDto> updateRole(RoleDto roleDto) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<RoleDto> saveRole(RoleDto roleDto) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity<Void> deleteRole(RoleDto roleDto) {
        try {
            roleServices.deleteRole(RoleMapper.mapToRole(roleDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
