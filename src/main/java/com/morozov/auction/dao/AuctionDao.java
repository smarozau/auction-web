package com.morozov.auction.dao;

import java.util.List;

import com.morozov.auction.model.Auction;
import com.morozov.auction.model.StatCdRef;

/**
 * @author Sergey Morozov
 *
 */

public interface AuctionDao {

	    public void save(Auction auction) throws Exception;
		
		public Auction findById(Integer auctionId) throws Exception;
		
		public List<Auction> findAll() throws Exception;
		
		public List<Auction> findByStatusCode(StatCdRef statCdRef) throws Exception;
		
		public int countAll() throws Exception;
		
	}

