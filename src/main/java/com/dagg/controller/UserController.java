package com.dagg.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dagg.dto.SignupDTO;
import com.dagg.dto.UserDTO;
import com.dagg.entity.Role;
import com.dagg.entity.User;
import com.dagg.repository.RoleRepository;
import com.dagg.repository.UserRepository;
import com.dagg.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * @author Dagg
 */

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody @Valid SignupDTO signupDTO) {
        System.out.println(signupDTO.toString());
        if (userRepository.existsByUsername(signupDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already taken!");
        }

        User user = modelMapper.map(signupDTO, User.class);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String enCryptPassword = passwordEncoder.encode(signupDTO.getPassword());
        user.setPassword(enCryptPassword);

        user.setStatus(User.UserStatus.NOT_ACTIVE);

        //acRepository.save(ac);

        userService.createUser(user);//Send mail active

        return ResponseEntity.ok().body("User registered successfully!");
    }

    @GetMapping("/active_account")
    public void activeAccount(@RequestParam(name = "id") int id) {
        userService.activeUser(id);
    }

    @GetMapping(value="/username/{username}")
    public UserDTO getUserByUsername(@PathVariable(name="username") String username) {
        User user = userService.getUserByUsername(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @GetMapping(value="/email/{email}")
    public UserDTO getUserByEmail(@PathVariable(name="email") String email) {
        User user = userService.findAccountByEmail(email);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @PostMapping("/password/password-changing")
    public ResponseEntity<?> changePassword(@RequestParam(value = "username") String username, @RequestParam(value = "newPassword") String newPassword) {
        System.out.println("username: " + username);
        System.out.println("new password: " + newPassword);

        User user = userService.getUserByUsername(username);

        BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
        String encryptPassword = pEncoder.encode(newPassword);
        user.setPassword(encryptPassword);

        userService.changePasswordUser(user);

        JSONObject message = new JSONObject();
        message.put("resultText", "User's password changed successfully");
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToUserForm {
    private String username;

    private String roleName;
}
