package com.morozov.auction.dao;

import java.util.List;

import com.morozov.auction.model.Lot;

/**
 * @author Sergey Morozov
 *
 */

public interface LotDao {

public void save(Lot lot) throws Exception;
	
	public Lot findById(Integer lotId) throws Exception;
	
	public List<Lot> findLotsByUserId(Integer userId) throws Exception;
	
	public List<Lot> findByAuctionId(Integer auctionId) throws Exception;
	
	public int countAll() throws Exception;
	
	public boolean deleteById (Integer lotId) throws Exception;
}
