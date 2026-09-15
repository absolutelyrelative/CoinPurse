package com.coinpurse.web.services;

import com.coinpurse.web.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface RoleServices {

    public Role saveRole(Role role);

    public void deleteRole(Role role) throws Exception;
}
