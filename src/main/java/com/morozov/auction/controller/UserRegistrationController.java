package com.morozov.auction.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.morozov.auction.helpers.LogHelper;
import com.morozov.auction.model.User;
import com.morozov.auction.model.UserRegistrationData;
import com.morozov.auction.model.validation.UserRegistrationValidator;
import com.morozov.auction.service.UserRegistrationService;


@Controller
public class UserRegistrationController {
	
	private Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
	
	@Autowired
	private UserRegistrationService userRegistrationService;
	
	@Autowired
	private UserRegistrationValidator registrationValidator;
	
	@Autowired
	private MessageSource messageSource;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(registrationValidator);
	}
	
	@RequestMapping(path="/registration", method=RequestMethod.GET)
	public String showRegistration(Model model, Locale locale) {
		model.addAttribute("title", messageSource.getMessage("label.registration",new Object[0], locale));
		model.addAttribute("registration", new UserRegistrationData(new User()));
		return "registration";
	}
	
	@RequestMapping(path="/registration", method=RequestMethod.POST)
	public String register(@Validated @ModelAttribute("registration") UserRegistrationData registration, BindingResult bindingResult,
			Model model, Locale locale) throws Exception {
		logger.info("Regestering user: " + registration.getUser());		
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "registration";
		}
		model.addAttribute("message", "You've been successfully registered");
		model.addAttribute("title", messageSource.getMessage("label.registration",new Object[0], locale));
		userRegistrationService.register(registration);
		return "registration-success";
	}
	
	@RequestMapping(path="registration/verify", method=RequestMethod.GET)
	public String confirm(@RequestParam("token") String verificationToken, Model model) throws Exception {
		userRegistrationService.confirm(verificationToken);
		model.addAttribute("message", "Your account has been activated");
		return "registration-success";
	}
	
}
