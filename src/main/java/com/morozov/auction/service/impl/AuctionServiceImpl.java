package com.morozov.auction.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.AuctionDao;
import com.morozov.auction.model.Auction;
import com.morozov.auction.service.AuctionService;

public class AuctionServiceImpl implements AuctionService {


	
	private AuctionDao auctionDao;
	
	public void setAuctionDao(AuctionDao auctionDao) {
		this.auctionDao = auctionDao;
	}
	
	@Override
	@Transactional
	public void createAuction(Auction auction) throws Exception {
		
		auctionDao.createAuction(auction);
	}

	@Override
	public Auction findById(Integer auctionId) throws Exception {
		
		return auctionDao.findById(auctionId);
	}

	@Override
	public List<Auction> findAll() throws Exception {
		
		return auctionDao.findAll();
	}

	@Override
	public List<Auction> findByStatusCode(Integer statusCode) throws Exception {
		
		return auctionDao.findByStatusCode(statusCode);
	}

	@Override
	public int countAll() throws Exception {
		
		return auctionDao.countAll();
	}

	@Override
	public void updateStatusCode(Integer auctionId, Integer statusCode) throws Exception {
		
		auctionDao.updateStatusCode(auctionId, statusCode);
		
	}

}
