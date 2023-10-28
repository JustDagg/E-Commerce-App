package com.dagg.validation;


import com.dagg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameNotExistsValidator implements ConstraintValidator<UserNameNotExists, String>{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !userService.isAccountExistsByUsername(value);
	}

}
