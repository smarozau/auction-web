package com.morozov.auction.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.morozov.auction.helpers.LogHelper;
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;
import com.morozov.auction.model.validation.SteadValidator;
import com.morozov.auction.service.SteadService;

@Controller
public class SteadController {

	private Logger logger = LoggerFactory.getLogger(SteadController.class);

	@Autowired
	private SteadService steadService;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private SteadValidator steadValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(steadValidator);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/steads", method = RequestMethod.GET)
	public String showSteads(ModelMap model, Locale locale) throws Exception {
		List<Stead> steads = steadService.findAll();
		String title = messageSource.getMessage("title.steads", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("steads", steads);
		return "steads";
	}
	
	@RequestMapping(path = "/stead/{id}", method = RequestMethod.GET)
	public String showStead(@PathVariable("id") int id, ModelMap model, Locale locale) throws Exception {
		Stead stead = steadService.findById(id);
		String title = messageSource.getMessage("title.stead", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("stead", stead);
		return "stead";
	}
	
	@RequestMapping(path = "/stead/{steadId}/edit", method = RequestMethod.GET)
	public String editStead(@PathVariable("steadId") int steadId, ModelMap model, Locale locale) throws Exception {
		Stead stead = steadService.findById(steadId);
		String title = messageSource.getMessage("title.stead", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("stead", stead);
		return "steadEdit";
	}
	
	@RequestMapping(path = "/stead/{steadId}/save", method = RequestMethod.POST)
	public String saveStead(@Validated Stead stead, BindingResult bindingResult, ModelMap model,
			@PathVariable("steadId") int steadId) throws Exception {
		if (stead.getSteadId() != null && stead.getSteadId() != steadId) {
			throw new SecurityException("Stead ID violation");
		}
		logger.debug("Saving stead:" + stead);
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "steadEdit";
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		stead.setOwner(user);
		steadService.save(stead);
		return "redirect:/stead/" + stead.getSteadId();
	}
	
	@RequestMapping(path = "/steads/{userId}", method = RequestMethod.GET)
	public String showUserSteads(@PathVariable("userId") int userId, ModelMap model, Locale locale) throws Exception {
		List<Stead> steads = steadService.findByUserId(userId);
		String title = messageSource.getMessage("title.mySteads", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("steads", steads);
		return "steads";
	}
	
	@RequestMapping(path="/steadAddition", method=RequestMethod.GET)
	public String showAddForm(Model model) {
		model.addAttribute("stead", new Stead());
		return "steadAddition";
	}
	
	@RequestMapping(path="/steadAddition", method=RequestMethod.POST)
	public String addStead(@Validated @ModelAttribute("stead") Stead stead, BindingResult bindingResult, Model model) throws Exception {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		stead.setOwner(user);
		logger.info("Adding stead: " + stead);		
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "steadAddition";
		}

		model.addAttribute("message", "You've been successfully added your stead");
		steadService.save(stead);
		return "redirect:/steads/" + stead.getOwner().getUserId();
	}
	
}
