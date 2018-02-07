package com.morozov.auction.model.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.morozov.auction.model.CheckboxSteadList;
import com.morozov.auction.security.authentication.LocalAuthenticationProvider;

public class SteadListValidator implements Validator {
	
	private Logger logger = LoggerFactory.getLogger(SteadListValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		
		return CheckboxSteadList.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CheckboxSteadList checkbox = (CheckboxSteadList) target;
		logger.info(""+checkbox.getSteads());
		if (checkbox.getSteads().size() == 0) {
			errors.rejectValue("steads", "required.steads", "Please select at least one steads!");
		}
		

	}

}
