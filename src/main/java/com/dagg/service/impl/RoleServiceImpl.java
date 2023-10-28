package com.dagg.service.impl;

import com.dagg.entity.Role;
import com.dagg.repository.RoleRepository;
import com.dagg.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 * @created 26/10/2023
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }
}
