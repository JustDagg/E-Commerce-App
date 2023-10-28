package com.dagg.service;

import com.dagg.dto.UserDTO;
import com.dagg.entity.Role;
import com.dagg.entity.User;
import com.dagg.form.UserFormForCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 */

public interface UserService {

    List<User> getAllListUsers();

    Page<User> getAllUsersPaging(Pageable pageable, String search);

    Role createRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    Optional<User> getUserById(int id);

    void deleteUserById(int id);

    void createUser(User user);

    void updateUser(int id, User user);

    void addNewUser(UserFormForCreating userFormForCreating);

    User findAccountByEmail(String email);

    void activeUser(int id);

    void changePasswordUser(User user);

    boolean isUserExists(int id);

    boolean isUserExistsByUsername(String username);

    User getUserByUsername(String username);

    boolean isAccountExistsByUsername(String username);

    Integer isUserPresent(User user);

    User saveUser(User user);

    void deleteMultipleUsers(List<Integer> ids);
}
