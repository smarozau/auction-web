package com.morozov.auction.model.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.morozov.auction.model.User;
import com.morozov.auction.model.UserRegistrationData;
import com.morozov.auction.service.UserService;



public class UserRegistrationValidator implements Validator {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		boolean isSupported = UserRegistrationData.class.equals(clazz);
		return isSupported;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UserRegistrationData registration = (UserRegistrationData)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required", "Password confirmation required");
		
		if ( !StringUtils.isEmpty(registration.getPassword()) 
				&& !StringUtils.isEmpty(registration.getConfirmPassword())
				&& !registration.getPassword().equals(registration.getConfirmPassword()) ) {
			errors.rejectValue("confirmPassword", "confirmPassword.mismatch", "Confirm password does not match password");
		}
		
		errors.pushNestedPath("user");
		ValidationUtils.invokeValidator(userValidator, registration.getUser(), errors);
		rejectIfEmailExist(registration, errors);
		rejectIfDisplayNameExist(registration, errors);
		
		errors.popNestedPath();
		
	}

	private void rejectIfEmailExist(UserRegistrationData registration, Errors errors) {
		String email = registration.getUser().getEmail();
		if ( StringUtils.isEmpty(email) )
			return;
		
		try {
			User user = userService.findByEmail(registration.getUser().getEmail());
			if (user != null) {
				errors.rejectValue("email", "email.exists", "User with this email already registered");
			}
		} catch(Exception e) {
			errors.rejectValue("email", "email.exists.nocheck", "Failed to verify email");
		}
	}
	
	private void rejectIfDisplayNameExist(UserRegistrationData registration, Errors errors) {
		String displayName = registration.getUser().getDisplayName();
		if ( StringUtils.isEmpty(displayName) )
			return;
		
		try {
			User user = userService.findByEmail(registration.getUser().getDisplayName());
			if (user != null) {
				errors.rejectValue("displayName", "displayName.exists", "User with this display name already registered");
			}
		} catch(Exception e) {
			errors.rejectValue("displayName", "displayName.exists.nocheck", "Failed to verify display name");
		}
	}

}
