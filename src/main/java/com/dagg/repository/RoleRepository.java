package com.dagg.repository;

import com.dagg.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dagg
 */

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
