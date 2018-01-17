package com.morozov.auction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.morozov.auction.model.Auction;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.Bid;
import com.morozov.auction.service.AuctionService;
import com.morozov.auction.service.BidService;
import com.morozov.auction.service.LotService;

@Controller
public class AuctionController {

	private Logger logger = LoggerFactory.getLogger(AuctionController.class);

	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private LotService lotService;
	
	@Autowired
	private BidService bidService;

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(path = "/auctions", method = RequestMethod.GET)
	public String showAuctions(ModelMap model, Locale locale) throws Exception {
		List<Auction> auctions = auctionService.findAll();
		String title = messageSource.getMessage("label.auctions", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("auctions", auctions);
		return "auctions";
	}
	
	@RequestMapping(path = "/auction/{id}", method = RequestMethod.GET)
	public String showAuction(@PathVariable("id") int id, ModelMap model, Locale locale) throws Exception {
		Auction auction = auctionService.findById(id);
		String status = auction.getStatusCode().getName();
		List<Lot> lots = lotService.findByAuctionId(id);
		int size = lots.size();
		auction.setLots(lots);
		Map<Lot, Bid> results = new HashMap<Lot, Bid>();
		for (Lot lot : lots) {
			Bid bid = bidService.findMaxBidForLot(lot.getLotId());
			results.put(lot, bid);
		}
		String startTime = auction.getFormattedTime(auction.getStartTime());
		String endTime = auction.getFormattedTime(auction.getEndTime());
		String title = messageSource.getMessage("label.auction", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("auction", auction);
		model.addAttribute("status", status);
		model.addAttribute("size", size);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("results", results);
		return "auction";

	}
	
	@RequestMapping(path = "/auctions/{statusCode}", method = RequestMethod.GET)
	public String showAuctionsByStatus(@PathVariable("statusCode") int statusCode, ModelMap model, Locale locale) throws Exception {
		List<Auction> auctions = auctionService.findByStatusCode(statusCode);
		String status;
		switch (statusCode) {
		case 1 : status = messageSource.getMessage("label.new", new Object[0], locale);
		break;
		case 2 : status = messageSource.getMessage("label.active", new Object[0], locale);
		break;
		case 3 : status = messageSource.getMessage("label.finished", new Object[0], locale);
		break;
		default: status = null;
		}
		String title = status + " " + messageSource.getMessage("label.auctions", new Object[0], locale);
		
		model.addAttribute("title", title);
		model.addAttribute("auctions", auctions);
		return "auctions";

	}
	
}
