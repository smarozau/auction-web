package com.morozov.auction.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.BidDao;
import com.morozov.auction.model.Bid;
import com.morozov.auction.service.BidService;

public class BidServiceImpl implements BidService {

private BidDao bidDao;
	
	public void setBidDao(BidDao bidDao) {
		this.bidDao = bidDao;
	}
	
	@Override
	@Transactional
	public void makeBid(Bid bid) throws Exception {
		
		bidDao.makeBid(bid);
	}

	@Override
	public List<Bid> findBidsByLotId(Integer lotId) throws Exception {
		
		return bidDao.findBidsByLotId(lotId);
	}

	@Override
	public BigDecimal findMaxBidForLot(Integer lotId) throws Exception {
		
		return bidDao.findMaxBidForLot(lotId);
	}

	@Override
	public void updateLotForWinner(Bid bid) throws Exception {
		bidDao.updateLotForWinner(bid);
		
	}

	@Override
	public Integer findBidderIdForLotByBid(BigDecimal maxBid, Integer lotId) throws Exception {
		return bidDao.findBidderIdForLotByBid(maxBid, lotId);
	}

}
