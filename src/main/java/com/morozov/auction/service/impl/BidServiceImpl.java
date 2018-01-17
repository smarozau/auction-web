package com.morozov.auction.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.BidDao;
import com.morozov.auction.model.Bid;
import com.morozov.auction.service.BidService;

public class BidServiceImpl implements BidService {

private Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);
	
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
	public Bid findMaxBidForLot(Integer lotId) throws Exception {
		
		return bidDao.findMaxBidForLot(lotId);
	}

}
