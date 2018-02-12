package com.morozov.auction.dao;

import java.util.Date;
import java.util.List;

import com.morozov.auction.model.Auction;

/**
 * @author Sergey Morozov
 *
 */

public interface AuctionDao {

	    public void createAuction(Auction auction) throws Exception;
		
		public Auction findById(Integer auctionId) throws Exception;
		
		public List<Auction> findAll() throws Exception;
		
		public List<Auction> findByStatusCode(Integer statusCode) throws Exception;
		
		public int countAll() throws Exception;
		
		void updateStatusCode(Integer auctionId, Integer statusCode) throws Exception;
		
//		public void cancelAuction(Auction auction) throws Exception;
		
	}

