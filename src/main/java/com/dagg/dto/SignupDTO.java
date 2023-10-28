package com.dagg.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Dagg
 * @created 26/10/2023
 */

@Data
public class SignupDTO {

    private String name;

    @NotBlank(message = "Username can not be null")
    private String username;

    private String password;

    private String email;

}
