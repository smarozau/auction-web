package com.morozov.auction.scheduler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.model.Auction;
import com.morozov.auction.model.Bid;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.User;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.service.AuctionService;
import com.morozov.auction.service.BidService;
import com.morozov.auction.service.LotService;
import com.morozov.auction.service.UserService;

@Service
@EnableScheduling
public class AuctionSchedulerService {

	private Logger logger = LoggerFactory.getLogger(AuctionSchedulerService.class);

	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private BidService bidService;

	@Autowired
	private LotService lotService;

	@Scheduled(fixedRate = 60000)
	@Transactional
	public void checkStartTimeTask() throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("Starting schedule service for auctions start time");
		}

		List<Auction> auctions = auctionService.findByStatusCode(1);

		for (Auction auction : auctions) {
			if (System.currentTimeMillis() >= auction.getStartTime().getTime()) {
				auctionService.updateStatusCode(auction.getId(), 2);
			}
		}
	}

	@Scheduled(fixedRate = 60000)
	public void checkEndTimeTask() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Starting schedule service for auctions end time");
		}

		List<Auction> auctions = auctionService.findByStatusCode(2);
		List<Auction> finishedAuctions = new ArrayList<Auction>();

		if (!auctions.isEmpty()) {
			for (Auction auction : auctions) {
				if (System.currentTimeMillis() >= auction.getEndTime().getTime()) {
					System.out.println("Auction ID " + auction.getId() + " " + auction.getStatusCode().getName());
					auctionService.updateStatusCode(auction.getId(), 3);
					finishedAuctions.add(auction);
					System.out.println("Auction ID " + auction.getId() + " " + auction.getStatusCode().getName());
				}
			}
		}

		if (!finishedAuctions.isEmpty()) {
			for (Auction finishedAuction : finishedAuctions) {
				List<Lot> lots = lotService.findByAuctionId(finishedAuction.getId());
				for (Lot lot : lots) {
					BigDecimal maxBid = bidService.findMaxBidForLot(lot.getLotId());
					User winner = userService.findById(bidService.findBidderIdForLotByBid(maxBid, lot.getLotId()));
					bidService.updateLotForWinner(new Bid(new LotMember(winner, lot), maxBid));
				}
			}
		}

	}
}
