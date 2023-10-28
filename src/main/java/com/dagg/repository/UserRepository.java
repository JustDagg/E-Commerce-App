package com.dagg.repository;

import com.dagg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 */

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    boolean existsByUsername(String username);

    @Query("SELECT us FROM User us WHERE us.username =:username")
    User findByUsername(String username);

    @Query("SELECT us FROM User us WHERE us.email =:email")
    User findAccountByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User us SET us.password = :newPassword, us.status = 0 WHERE us.id = :id")
    void changePasswordUser(@Param("id") int id, @Param("newPassword") String newPassword);

    boolean existsByEmail(String email);

    @Query("SELECT us FROM User us WHERE us.email =:email")
    Optional<User> findUserByEmail(String email);

    User getCustomerByEmailAndName(String email, String name);

    @Modifying
    @Transactional
    @Query("DELETE User us WHERE us.id IN(:Ids)")
    void deleteMultipleUsers(List<Integer> Ids);
}
