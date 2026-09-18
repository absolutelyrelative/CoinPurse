package com.coinpurse.web.services;

import com.coinpurse.web.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface RoleServices {

    public List<Role> getAll();

    public Role getRoleById(Long roleId);

    public Role saveRole(Role role);

    public void deleteRole(Role role);
}
