package com.morozov.auction.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.User;
import com.morozov.auction.model.validation.LotMemberValidator;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.service.LotMemberService;
import com.morozov.auction.service.LotService;

@Controller
public class LotController {

	private Logger logger = LoggerFactory.getLogger(LotController.class);

	@Autowired
	private LotService lotService;

	@Autowired
	private LotMemberService lotMemberService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private LotMemberValidator lotMemberValidator;

	@InitBinder("lotMember")
	private void initLotMemberBinder(WebDataBinder binder) {
		binder.setValidator(lotMemberValidator);

	}
	


	@RequestMapping(path = "/lot/{lotId}", method = RequestMethod.GET)
	public String showLots(@PathVariable("lotId") int lotId, ModelMap model, Locale locale) throws Exception {
		Lot lot = lotService.findById(lotId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LotMember lotMember = new LotMember(user, lot);
		String title = messageSource.getMessage("label.lot", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("lot", lot);
		model.addAttribute("lotMember", lotMember);
		return "lot";
	}

	@RequestMapping(path = "/lot/{lotId}", method = RequestMethod.POST)
	public String becomeLotMember(@PathVariable("lotId") int lotId,	@Validated @ModelAttribute("lotMember") LotMember lotMember, 
			BindingResult bindingResult, ModelMap model,Locale locale) throws Exception {
		Lot lot = lotService.findById(lotId);
		logger.info("Saving lot's member: " + lotMember);
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "lot";
		}
		lotMemberService.saveLotMember(lotMember);
		return "redirect:/lot/" + lot.getLotId();
	}

	
}
