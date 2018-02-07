package com.morozov.auction.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.LotDao;
import com.morozov.auction.model.Lot;
import com.morozov.auction.service.LotService;

public class LotServiceImpl implements LotService {

private Logger logger = LoggerFactory.getLogger(LotServiceImpl.class);
	
	private LotDao lotDao;
	
	public void setlotDao(LotDao lotDao) {
		this.lotDao = lotDao;
	}
	
	@Override
	@Transactional
	public void save(Lot lot) throws Exception {
		
		lotDao.save(lot);
	}

	@Override
	public Lot findById(Integer lotId) throws Exception {
		
		return lotDao.findById(lotId);
	}

	@Override
	public List<Lot> findByAuctionId(Integer auctionId) throws Exception {
		
		return lotDao.findByAuctionId(auctionId);
	}

	@Override
	public int countAll() throws Exception {
		
		return lotDao.countAll();
	}

	@Override
	public boolean deleteById(Integer lotId) throws Exception {
		
		return lotDao.deleteById(lotId);
	}

	@Override
	public List<Lot> findLotsByUserId(Integer userId) throws Exception {
		
		return lotDao.findLotsByUserId(userId);
	}

	@Override
	public List<Lot> findByAuctionIdAndStatusCode(Integer auctionId, Integer statusCode) throws Exception {
		
		return lotDao.findByAuctionIdAndStatusCode(auctionId, statusCode);
	}

}
