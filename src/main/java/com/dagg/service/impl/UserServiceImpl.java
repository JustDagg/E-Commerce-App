package com.dagg.service.impl;

import com.dagg.dto.UserDTO;
import com.dagg.entity.Product;
import com.dagg.entity.Role;
import com.dagg.entity.User;
import com.dagg.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.dagg.event.OnUpdatePasswordEvent;
import com.dagg.form.UserFormForCreating;
import com.dagg.repository.RoleRepository;
import com.dagg.repository.UserRepository;
import com.dagg.service.UserService;
import com.dagg.specification.ProductSpecification;
import com.dagg.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the DB");
            throw new UsernameNotFoundException("User not found in the DB");
        } else {
            log.info("User found in the DB: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public List<User> getAllListUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUsersPaging(Pageable pageable, String search) {
        Specification<User> where = UserSpecification.buildWhere(search);
        return userRepository.findAll(where, pageable);
    }

    @Override
    public Role createRole(Role role) {
        log.info("Saving new role {} to the DB", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);

        //Send mail active
        sendConfirmUserRegistrationViaEmail(user.getEmail());
    }

    private void sendConfirmUserRegistrationViaEmail(String email) {
        eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
    }

    @Override
    public void updateUser(int id, User user) {
        userRepository.save(user);
    }

    @Override
    public void addNewUser(UserFormForCreating userFormForCreating) {
        User us = modelMapper.map(userFormForCreating, User.class);
        User uss = userRepository.save(us);
        System.out.println("User save: ");
        System.out.println(uss);
    }

    @Override
    public User findAccountByEmail(String email) {
        return userRepository.findAccountByEmail(email);
    }

    @Override
    public void activeUser(int id) {
        User user = userRepository.getById(id);
        user.setStatus(User.UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void changePasswordUser(User user) {
        System.out.println(user.getId());
        System.out.println(user.getPassword());

        userRepository.changePasswordUser(user.getId(), user.getPassword());
        //Send mail active
        sendConfirmUpdatePasswordViaEmail(user);
    }

    private void sendConfirmUpdatePasswordViaEmail(User user) {
        eventPublisher.publishEvent(new OnUpdatePasswordEvent(user));
    }

    @Override
    public boolean isUserExists(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean isUserExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isAccountExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Integer isUserPresent(User user) {
        User user1 = userRepository.getCustomerByEmailAndName(user.getEmail(),user.getName());
        return user1!=null ? user1.getId(): null ;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteMultipleUsers(List<Integer> ids) {
        userRepository.deleteMultipleUsers(ids);
    }

}
