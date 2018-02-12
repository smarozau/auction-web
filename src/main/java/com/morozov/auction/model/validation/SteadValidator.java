package com.morozov.auction.model.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.morozov.auction.model.Stead;

public class SteadValidator implements Validator {

	// @Autowired
	// private UserValidator userValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return Stead.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Stead stead = (Stead) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "steadCountry", "steadCountry.required",
				"Stead's country value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "steadRegion", "steadRegion.required",
				"Stead's region value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "steadCity", "steadCity.required",
				"Stead's city value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "steadAddress", "steadAddress.required",
				"Stead's address value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coordinates", "coordinates.required",
				"Stead's coordinates value is required");
		ValidationUtils.rejectIfEmpty(errors, "size", "size.required", "Stead's size value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required",
				"Stead's description value is required");
		ValidationUtils.rejectIfEmpty(errors, "reservePrice", "reservePrice.required",
				"Stead's price value is required");

		if (stead.getSize() == null){
			errors.rejectValue("size", "size.required", "Stead's size value is required");
		} else if (stead.getSize().intValue() <= 0) {
				errors.rejectValue("size", "steadSize.negativevalue", "Negative value");
			}
		
		if(stead.getReservePrice() == null) {
			errors.rejectValue("reservePrice", "reservePrice.required",	"Stead's price value is required");
			} else if (stead.getReservePrice().signum() == 0) {
				errors.rejectValue("reservePrice", "steadReservePrice.zero", "Cannot be zero");
			}

			
	}
}
