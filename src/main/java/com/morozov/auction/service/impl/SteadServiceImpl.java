package com.morozov.auction.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.SteadDao;
import com.morozov.auction.model.Stead;
import com.morozov.auction.service.SteadService;

public class SteadServiceImpl implements SteadService {

private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private SteadDao steadDao;
	
	public void setSteadDao(SteadDao steadDao) {
		this.steadDao = steadDao;
	}
	
	@Override
	@Transactional
	public void save(Stead stead) throws Exception {
		
		steadDao.save(stead);
	}

	@Override
	public Stead findById(Integer steadId) throws Exception {
		
		return steadDao.findById(steadId);
	}

	@Override
	public List<Stead> findByCountry(String country) throws Exception {
		
		return steadDao.findByCountry(country);
	}

	@Override
	public List<Stead> findByRegion(String region) throws Exception {
		
		return steadDao.findByRegion(region);
	}

	@Override
	public List<Stead> findByUserId(Integer userId) throws Exception {
		
		return steadDao.findByUserId(userId);
	}
	
	@Override
	public List<Stead> findAvailableByUserId(Integer userId, Integer auctionId) throws Exception {
		
		return steadDao.findAvailableByUserId(userId, auctionId);
	}

	@Override
	public List<Stead> findByCity(String city) throws Exception {
		
		return steadDao.findByCity(city);
	}

	@Override
	public List<Stead> findByReservePrice(BigDecimal reservePrice) {
		
		return steadDao.findByReservePrice(reservePrice);
	}

	@Override
	public int countAll() throws Exception {
		
		return steadDao.countAll();
	}

	@Override
	@Transactional
	public boolean deleteById(Integer steadId) throws Exception {
		
		return steadDao.deleteById(steadId);
	}

	@Override
	public List<Stead> findAll() throws Exception {
		
		return steadDao.findAll();
	}

}
