package com.morozov.auction.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.morozov.auction.helpers.LogHelper;
import com.morozov.auction.model.Auction;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;
import com.morozov.auction.model.validation.AuctionValidator;
import com.morozov.auction.model.validation.BidValidator;
import com.morozov.auction.model.validation.SteadListValidator;
import com.morozov.auction.model.Bid;
import com.morozov.auction.model.CheckboxSteadList;
import com.morozov.auction.service.AuctionService;
import com.morozov.auction.service.BidService;
import com.morozov.auction.service.LotService;
import com.morozov.auction.service.SteadService;

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
	private SteadService steadService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SteadListValidator steadListValidator;

	@Autowired
	private AuctionValidator auctionValidator;
	
	@Autowired
	private BidValidator bidValidator;

	@InitBinder("auction")
	private void initAuctionBinder(WebDataBinder binder) {
		binder.setValidator(auctionValidator);
	}

	@InitBinder("checkbox")
	private void initCheckboxBinder(WebDataBinder binder) {
		binder.setValidator(steadListValidator);
	}
	
	@InitBinder("bid")
	private void initBidBinder(WebDataBinder binder) {
		binder.setValidator(bidValidator);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
		List<Lot> finishedLots = lotService.findByAuctionIdAndStatusCode(auction.getId(), 3);
		Map<Lot, Bid> results = new HashMap<Lot, Bid>();
		Map<Lot, Bid> bids = new HashMap<Lot,Bid>();
		List<Bid> lotBids  = new LinkedList<Bid>();
		for (Lot finishedLot : lots) {
			if (finishedLots.contains(finishedLot)) {
				Bid maxBid = bidService.findMaxBidForLot(finishedLot.getLotId());
				results.put(finishedLot, maxBid);
			}
		}
		for (Lot lot : lots) {
			lotBids = bidService.findBidsByLotId(lot.getLotId());
			if (!lotBids.isEmpty()) {
				Bid lastBid = lotBids.get(lotBids.size()-1);
				bids.put(lot, lastBid);
			}
		}
		
		// String startTime = auction.getFormattedTime(auction.getStartTime());
		// String endTime = auction.getFormattedTime(auction.getEndTime());
		String title = messageSource.getMessage("label.auction", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("results", results);
		model.addAttribute("auction", auction);
		model.addAttribute("status", status);
		model.addAttribute("size", size);
		model.addAttribute("lots", lots);
		model.addAttribute("bids", bids);
		model.addAttribute("bid", new Bid());

		return "auction";

	}
	
	@RequestMapping(path = "/auction/{id}/makeBid", method = RequestMethod.POST)
	public String makeBid(@Validated @ModelAttribute("bid") Bid bid, @PathVariable("id") int id, @RequestParam("steadId") int steadId,
			Model model, BindingResult bindingResult, Locale locale) throws Exception {
		Auction auction = auctionService.findById(id);
		logger.info("Making bid: " + bid);
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "auction";
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Stead stead = steadService.findById(steadId);
		Lot lot = new Lot(auction, stead);
		LotMember lotMember = new LotMember(user, lot);
		bid.setLotMember(lotMember);
		bidService.makeBid(bid);
		return "redirect:/auction/" + auction.getId();
	}

	@RequestMapping(path = "auction/{id}/selectLots", method = RequestMethod.GET)
	public String showLotsForSelect(@PathVariable("id") int id, Model model, Locale locale) throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Stead> steads = steadService.findByUserId(user.getUserId());
		CheckboxSteadList checkbox = new CheckboxSteadList();
		checkbox.setSteads(steads);
		String title = messageSource.getMessage("title.selectLots", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("checkbox", checkbox);
		return "selectLots-backup";
	}

	@RequestMapping(path = "auction/{id}/selectLots", method = RequestMethod.POST)
	public String selectLots(@PathVariable("id") int id, @Validated @ModelAttribute("checkbox") CheckboxSteadList checkbox,
			BindingResult bindingResult, Model model, Locale loale) throws Exception {
		Auction auction = auctionService.findById(id);
		logger.info("Selecting steads: " + checkbox.getSteads());
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "selectLots";
		}
		List<Lot> auctionLots = auction.getLots();
		List<Stead> steads = checkbox.getSteads();
		for (Stead stead : steads) {
			Lot lot = new Lot(auction, stead);
			if (auctionLots.contains(lot))
				continue;
			lotService.save(lot);
		}
		return "redirect:/auction/" + auction.getId();

	}

	@RequestMapping(path = "/auctions/{statusCode}", method = RequestMethod.GET)
	public String showAuctionsByStatus(@PathVariable("statusCode") int statusCode, ModelMap model, Locale locale)
			throws Exception {
		List<Auction> auctions = auctionService.findByStatusCode(statusCode);
		String status;
		switch (statusCode) {
		case 1:
			status = messageSource.getMessage("label.new", new Object[0], locale);
			break;
		case 2:
			status = messageSource.getMessage("label.active", new Object[0], locale);
			break;
		case 3:
			status = messageSource.getMessage("label.finished", new Object[0], locale);
			break;
		default:
			status = null;
		}
		String title = status + " " + messageSource.getMessage("label.auctions", new Object[0], locale);

		model.addAttribute("title", title);
		model.addAttribute("auctions", auctions);
		return "auctions";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/createAuction", method = RequestMethod.GET)
	public String showAuctionForm(Model model, Locale locale) {
		model.addAttribute("title", messageSource.getMessage("label.newAuction", new Object[0], locale));
		model.addAttribute("auction", new Auction());
		return "createAuction";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/createAuction", method = RequestMethod.POST)
	public String showSaveAuction(@Validated @ModelAttribute Auction auction, BindingResult bindingResult, Model model,
			Locale locale) throws Exception {
		logger.info("Saving auction: " + auction);
		if (bindingResult.hasErrors()) {
			LogHelper.logBindingResults(logger, bindingResult);
			return "createAuction";
		}
		
		auctionService.createAuction(auction);
		return "auctions";
	}

}
