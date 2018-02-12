package com.morozov.auction.exception.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(DataAccessException.class)
//	public String handleDataAccessException(DataAccessException e, Model model) {
//		model.addAttribute("errorMessage", "Failed to access database");
//		model.addAttribute("errorMessageDetails", e.getMessage());
//		return "errors/error";
//	}
//
//	@ExceptionHandler(AuthenticationServiceException.class)
//	public String handleAuthenticationException(Exception e, Model model) {
//		model.addAttribute("error", "Error");
//		model.addAttribute("msg", e.getMessage());
//		System.out.println("handleException");
//		return "login";
//	}
//
//	@ExceptionHandler(NoHandlerFoundException.class)
//	public String handleError404(NoHandlerFoundException e, Model model) {
//		model.addAttribute("errorMessage", "Error");
//		model.addAttribute("errorMessageDetails", e.getMessage());
//		return "errors/error";
//		
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public String handleException(Exception e, Model model) {
//		model.addAttribute("errorMessage", "Error");
//		model.addAttribute("errorMessageDetails", e.getMessage());
//		return "errors/error";
//	}
//
}
