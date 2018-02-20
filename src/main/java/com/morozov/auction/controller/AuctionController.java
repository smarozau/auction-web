package com.morozov.auction.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
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
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;
import com.morozov.auction.model.validation.AuctionValidator;
import com.morozov.auction.model.Bid;
import com.morozov.auction.service.AuctionService;
import com.morozov.auction.service.BidService;
import com.morozov.auction.service.LotMemberService;
import com.morozov.auction.service.LotService;
import com.morozov.auction.service.SteadService;
import com.morozov.auction.service.UserService;

@Controller
public class AuctionController {

	private Logger logger = LoggerFactory.getLogger(AuctionController.class);

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private LotService lotService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LotMemberService lotMemberService;

	@Autowired
	private BidService bidService;

	@Autowired
	private SteadService steadService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AuctionValidator auctionValidator;

	@InitBinder("auction")
	private void initAuctionBinder(WebDataBinder binder) {
		binder.setValidator(auctionValidator);
	}

	@Secured("ROLE_ADMIN")
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
		List<Integer> idFinishedLots = lotService.findByAuctionIdAndStatusCode(auction.getId(), 3);
		Map<Integer, BigDecimal> results = new HashMap<Integer, BigDecimal>();
		Map<Lot, Bid> bids = new HashMap<Lot, Bid>();
		List<Bid> lotBids = new LinkedList<Bid>();
		Map<Integer, String> winners = new HashMap<Integer,String>(); 
		for (Lot finishedLot : lots) {
			if (idFinishedLots != null && idFinishedLots.contains(finishedLot.getLotId())) {
				BigDecimal maxBid = bidService.findMaxBidForLot(finishedLot.getLotId());
				if(maxBid != null) {
				results.put(finishedLot.getLotId(), maxBid);
				String displayNameWinner = userService.findById(bidService.findBidderIdForLotByBid(maxBid, finishedLot.getLotId())).getDisplayName();
				System.out.println("1" + userService.findById(bidService.findBidderIdForLotByBid(maxBid, finishedLot.getLotId())).getDisplayName()+"1"); 
				winners.put(finishedLot.getLotId(), displayNameWinner);
			}
		}
		}
		Map<Lot,List<User>> members = new HashMap<>();
		for (Lot lot : lots) {
			lotBids = bidService.findBidsByLotId(lot.getLotId());
			if (!lotBids.isEmpty()) {
				Bid lastBid = lotBids.get(lotBids.size() - 1);
				bids.put(lot, lastBid);
			}
			List<User> lotMembers = lotMemberService.findByLotId(lot.getLotId());
			members.put(lot, lotMembers);
		}

		String title = messageSource.getMessage("label.auction", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("results", results);
		model.addAttribute("auction", auction);
		model.addAttribute("status", status);
		model.addAttribute("size", size);
		model.addAttribute("lots", lots);
		model.addAttribute("bids", bids);
		model.addAttribute("members", members);
		model.addAttribute("winners", winners);

		return "auction";

	}

	@RequestMapping(path = "auction/{id}/selectLots", method = RequestMethod.GET)
	public String showLotsForSelect(@PathVariable("id") int id,
			@RequestParam(value = "steads", required = false) int[] steads, Model model, Locale locale)
			throws Exception {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Stead> userSteads = steadService.findAvailableByUserId(user.getUserId(), id);
		String title = messageSource.getMessage("title.selectLots", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("steads", userSteads);
		return "selectLots";
	}

	@RequestMapping(path = "auction/{id}/selectLots", method = RequestMethod.POST)
	public String selectLots(@PathVariable("id") int id, @RequestParam(value = "steads", required = false) int[] steads,
			Model model, Locale locale) throws Exception {
		Auction auction = auctionService.findById(id);
		logger.info("Selecting steads: " + steads);
		if (steads == null) {
			model.addAttribute("error", messageSource.getMessage("steads.error", new Object[0], locale));
			return "forward:/auction/" + id + "/selectLots";
		}
		List<Lot> auctionLots = auction.getLots();
		List<Lot> selectLots = new ArrayList<Lot>();
		for (int steadId : steads) {
			Lot lot = new Lot(auction, new Stead(steadId));
			if (auctionLots.contains(lot)) {
				continue;
			} else {
				selectLots.add(lot);
			}
		}
		lotService.saveBatch(selectLots);
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

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/createAuction", method = RequestMethod.GET)
	public String showAuctionForm(ModelMap model, Locale locale) {
		model.addAttribute("title", messageSource.getMessage("label.newAuction", new Object[0], locale));
		model.addAttribute("auction", new Auction());
		return "createAuction";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/createAuction", method = RequestMethod.POST)
	public String showSaveAuction(@Validated @ModelAttribute Auction auction, BindingResult bindingResult, ModelMap model,
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