package com.dagg.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Dagg
 * @created 26/10/2023
 */

@Data
public class UserFormForUpdating {

    private int id;

    private String name;

    private String username;

    private String password;

    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "The status mustn't be null value")
    private String status;

}
