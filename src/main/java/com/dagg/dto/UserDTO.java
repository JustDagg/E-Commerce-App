package com.dagg.dto;

import com.dagg.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Dagg
 * @created 26/10/2023
 */

@Data
@RequiredArgsConstructor
public class UserDTO {

    private int id;

    private String name;

    private String username;

    private String password;

    private String email;

    private List<RoleDTO> roles;

}
