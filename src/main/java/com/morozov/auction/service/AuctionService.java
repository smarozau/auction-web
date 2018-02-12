package com.morozov.auction.service;

import java.util.List;

import com.morozov.auction.model.Auction;

/**
 * @author Sergey Morozov
 *
 */

public interface AuctionService {

	    public void createAuction(Auction auction) throws Exception;
		
		public Auction findById(Integer auctionId) throws Exception;
		
		public List<Auction> findAll() throws Exception;
		
		public List<Auction> findByStatusCode(Integer statusCode) throws Exception;
		
		public int countAll() throws Exception;
		
		void updateStatusCode(Integer auctionId, Integer statusCode) throws Exception;
		
//		public void cancelAuction(Auction auction) throws Exception;
		
	}

