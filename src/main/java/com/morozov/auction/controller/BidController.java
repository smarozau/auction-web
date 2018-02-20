package com.morozov.auction.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.morozov.auction.model.Bid;
import com.morozov.auction.service.BidService;

@Controller
public class BidController {

	@Autowired
	private BidService bidService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(path="/bids/{userId}")
	public String showUsersBids(@PathVariable("userId") int userId, ModelMap model, Locale locale) throws Exception {
		List<Bid> bids = bidService.findBidsByUserId(userId);
		String title = messageSource.getMessage("title.bidsHistory", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("bids", bids);
		return "bids";
		
	}
}
