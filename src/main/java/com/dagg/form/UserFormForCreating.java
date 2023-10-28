package com.dagg.form;

import com.dagg.validation.UserNameNotExists;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Dagg
 * @created 26/10/2023
 */

@Data
public class UserFormForCreating {

    private int id;

    private String name;

    @UserNameNotExists
    @NotBlank(message = "The username cannot be blank")
    private String username;

    private String password;

    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "The status mustn't be null value")
    private String status;

}
