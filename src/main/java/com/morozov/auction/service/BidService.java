package com.morozov.auction.service;

import java.math.BigDecimal;
import java.util.List;

import com.morozov.auction.model.Bid;
import com.morozov.auction.model.User;

public interface BidService {

	public void makeBid(Bid bid) throws Exception;
	
	public List<Bid> findBidsByLotId(Integer lotId) throws Exception;
	
	public Bid findMaxBidForLot(Integer lotId) throws Exception;
}
