package com.dagg.service;

import com.dagg.entity.Role;

import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 * @created 26/10/2023
 */
public interface RoleService {

    List<Role> getAllRole();
    Optional<Role> findRoleById(int id);

    void deleteRoleById(int id);
}
