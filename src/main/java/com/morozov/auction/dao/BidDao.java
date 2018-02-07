package com.morozov.auction.dao;

import java.util.List;

import com.morozov.auction.model.Bid;

public interface BidDao {

	public void makeBid(Bid bid) throws Exception;
	
	public List<Bid> findBidsByLotId(Integer lotId) throws Exception;
	
	public Bid findMaxBidForLot(Integer lotId) throws Exception;
}
