package com.coinpurse.web.services.impl;

import com.coinpurse.web.infrastructure.client.CurrencyApiClient;
import com.coinpurse.web.model.Role;
import com.coinpurse.web.repository.RoleRepository;
import com.coinpurse.web.services.RoleServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServicesImpl implements RoleServices {

    private static final Logger log = LoggerFactory.getLogger(RoleServicesImpl.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServicesImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void deleteRole(Role role) throws Exception {
        if(role.getId() != null) {
            roleRepository.deleteById(role.getId());
        } else {
            log.error("Role id to delete not specified");
            throw new Exception("Role id to delete not specified");
        }
    }
}
