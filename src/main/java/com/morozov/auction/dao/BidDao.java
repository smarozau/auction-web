package com.morozov.auction.dao;

import java.math.BigDecimal;
import java.util.List;

import com.morozov.auction.model.Bid;

public interface BidDao {

	public void makeBid(Bid bid) throws Exception;
	
	public List<Bid> findBidsByLotId(Integer lotId) throws Exception;
	
	public BigDecimal findMaxBidForLot(Integer lotId) throws Exception;
	
	void updateLotForWinner(Bid bid) throws Exception;

	public Integer findBidderIdForLotByBid(BigDecimal maxBid, Integer lotId) throws Exception;
}
