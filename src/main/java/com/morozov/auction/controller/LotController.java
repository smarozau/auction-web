package com.morozov.auction.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.morozov.auction.model.Auction;
import com.morozov.auction.model.Bid;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.User;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.service.BidService;
import com.morozov.auction.service.LotMemberService;
import com.morozov.auction.service.LotService;
import com.morozov.auction.service.UserService;

@Controller
public class LotController {

	private Logger logger = LoggerFactory.getLogger(LotController.class);

	@Autowired
	private LotService lotService;

	@Autowired
	private BidService bidService;

	@Autowired
	private UserService userService;

	@Autowired
	private LotMemberService lotMemberService;

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(path = "/lot/{lotId}", method = RequestMethod.GET)
	public String showLots(@PathVariable("lotId") int lotId, ModelMap model, Locale locale) throws Exception {
		Lot lot = lotService.findById(lotId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Lot> lots = lotMemberService.findByLotMemberId(user.getUserId());
		String title = messageSource.getMessage("label.lot", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("lot", lot);
		model.addAttribute("user", user);

		if (!lots.contains(lot)) {
			return "lot";
		} else {
			return "redirect:/lot/" + lot.getLotId() + "/makeBid";
		}
	}

	@RequestMapping(path = "/lot/{lotId}", method = RequestMethod.POST)
	public String becomeLotMember(@PathVariable("lotId") int lotId, @RequestParam("userId") int userId, ModelMap model,
			Locale locale) throws Exception {
		Lot lot = lotService.findById(lotId);
		logger.info("Saving lot's member for : " + lot);
		if (lot.getStead().getOwner().getUserId() == userId) {
			String error = messageSource.getMessage("lot.error", new Object[0], locale);
			model.addAttribute("error", error);
			return "lot";
		}
		User user = userService.findById(userId);
		List<Lot> lots = lotMemberService.findByLotMemberId(userId);
		
		if (lots != null && lots.contains(lot)) {
			String error = messageSource.getMessage("lotMember.error", new Object[0], locale);
			model.addAttribute("error", error);
			return "lot";
		} else {
			LotMember lotMember = new LotMember(user, lot);
			lotMemberService.saveLotMember(lotMember);
			return "redirect:/auction/" + lot.getAuction().getId();
			
		}
	}

	@RequestMapping(path = "/lot/{lotId}/makeBid", method = RequestMethod.GET)
	public String showFormBid(@PathVariable("lotId") int lotId, @RequestParam(value = "makeBid", required = false) Integer makeBid, ModelMap model, Locale locale) throws Exception {
		Lot lot = lotService.findById(lotId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Lot> lots = lotMemberService.findByLotMemberId(user.getUserId());
		List<Integer> lotIDs = new ArrayList<Integer>();
		for (Lot ID : lots) {
			lotIDs.add(ID.getLotId());
		}
		if (lots == null || !lotIDs.contains(lotId)) {
			return "redirect:/lot/" + lotId;
		}
		Auction auction = lot.getAuction();
		Bid bid = new Bid();
		List<Bid> bids = bidService.findBidsByLotId(lotId);
		Bid previousBid = new Bid();
		if (bids.isEmpty()) {
			previousBid.setBid(lot.getStead().getReservePrice());
		} else {
		previousBid = bids.get(bids.size()-1);
		}
		bid.setLotMember(new LotMember(user, lot));
		String title = messageSource.getMessage("label.lot", new Object[0], locale);
		model.addAttribute("title", title);
		model.addAttribute("auction", auction);
		model.addAttribute("lot", lot);
		model.addAttribute("bid", bid);
		model.addAttribute("previousBid", previousBid);
		return "makeBid";
	}
	
	@RequestMapping(path = "/lot/{lotId}/save", method = RequestMethod.POST)
	public String makeBid(@PathVariable("lotId") int lotId,  @RequestParam(value = "makeBid", required = false) Integer makeBid,
			ModelMap model, Locale locale) throws Exception {
		logger.debug("Making bid:" + makeBid + "for lot id:" + lotId);
		String title = messageSource.getMessage("label.lot", new Object[0], locale);
		Lot lot = lotService.findById(lotId);
		Auction auction = lot.getAuction();
		List<Bid> bids = bidService.findBidsByLotId(lotId);
		Bid previousBid = new Bid();
		if (bids.isEmpty()) {
			previousBid.setBid(lot.getStead().getReservePrice());
		} else {
		previousBid = bids.get(bids.size()-1);
		}
		model.addAttribute("title", title);
		model.addAttribute("auction", auction);
		model.addAttribute("lot", lot);
		model.addAttribute("previousBid", previousBid);
		
		if (makeBid == null) {
			model.addAttribute("msg", messageSource.getMessage("bid.notEmpty", new Object[0], locale));
			return "makeBid";
		}
		if (makeBid < 0) {
			model.addAttribute("msg", messageSource.getMessage("bid.negativeValue", new Object[0], locale));
			return "makeBid";
		}
		
		if(previousBid.getBid().intValue() >= makeBid) {
			model.addAttribute("msg", messageSource.getMessage("bid.lessThanNecessary", new Object[0], locale));
			return "makeBid";
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		bidService.makeBid(new Bid(new LotMember(user, lot),new BigDecimal(makeBid)));
		return "redirect:/lot/" + lotId + "/makeBid";
		
	}

}
