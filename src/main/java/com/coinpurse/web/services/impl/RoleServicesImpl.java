package com.coinpurse.web.services.impl;

import com.coinpurse.web.domain.exceptions.ResourceInUseException;
import com.coinpurse.web.domain.exceptions.ResourceNotFoundException;
import com.coinpurse.web.infrastructure.client.CurrencyApiClient;
import com.coinpurse.web.model.Role;
import com.coinpurse.web.repository.RoleRepository;
import com.coinpurse.web.repository.UserRepository;
import com.coinpurse.web.services.RoleServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.coinpurse.web.constants.ErrorMessages.ROLE_IN_USE;
import static com.coinpurse.web.constants.ErrorMessages.ROLE_NOT_FOUND;

@Service
public class RoleServicesImpl implements RoleServices {

    private static final Logger log = LoggerFactory.getLogger(RoleServicesImpl.class);

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Autowired
    public RoleServicesImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Role role) {
        Role roleToDelete = roleRepository.findById(role.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ROLE_NOT_FOUND));

        if(userRepository.existsByRoles_Id(role.getId())) {
            throw new ResourceInUseException(ROLE_IN_USE);
        }

        roleRepository.delete(roleToDelete);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException(ROLE_NOT_FOUND)
        );
    }
}
