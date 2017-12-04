package com.morozov.auction.helpers;

import org.slf4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class LogHelper {
	
	public static void logBindingResults(Logger logger, BindingResult bindingResult) {
		if (logger.isDebugEnabled()) {
			logger.debug("User has errors: " + bindingResult.hasErrors());
			for(ObjectError error : bindingResult.getAllErrors()) {
				logger.debug("Validation error: " + error.getCode() + ", " + error.getDefaultMessage());
			}
		}
	}
}
