package com.morozov.auction.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String showLoginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Locale locale) {
		if(error != null) {
			model.addAttribute("error", messageSource.getMessage("login.error", new Object[0], locale));
		}
		if (logout != null) {
			model.addAttribute("msg", messageSource.getMessage("logout", new Object[0], locale));
		}
		return "login";
	}

}
