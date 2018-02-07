package com.morozov.auction.model.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.morozov.auction.model.User;



public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> c) {		
		return User.class.equals(c);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayName", "displayName.required", "Display name value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required", "First name value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required", "Last name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "country.required", "Country is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.required", "City is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required", "Address is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.required", "Phone is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "Email is required");
		
	}

}
