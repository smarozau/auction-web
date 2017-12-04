package com.morozov.auction.dao;

import java.util.List;

import com.morozov.auction.model.Lot;
import com.morozov.auction.model.Stead;

/**
 * @author Sergey Morozov
 *
 */

public interface LotDao {

public void save(Lot lot) throws Exception;
	
	public Stead findById(Integer lotId) throws Exception;
	
	public List<Lot> findLotsByUserId(Integer userId) throws Exception;
	
	public List<Lot> findByAuctionId(Integer auctionId) throws Exception;
	
	public List<Lot> findByLotMemberId(Integer lotMemberId) throws Exception;
	
	public int countAll() throws Exception;
	
	public boolean deleteById (Integer lotId) throws Exception;
}
