package com.morozov.auction.service;

import java.util.List;

import com.morozov.auction.model.Lot;

/**
 * @author Sergey Morozov
 *
 */

public interface LotService {

    public void save(Lot lot) throws Exception;
    
    public void saveBatch(List<Lot> lots) throws Exception;
	
	public Lot findById(Integer lotId) throws Exception;
	
	public List<Lot> findLotsByUserId(Integer userId) throws Exception;
	
	public List<Lot> findByAuctionId(Integer auctionId) throws Exception;
	
	public List<Integer> findByAuctionIdAndStatusCode(Integer auctionId, Integer statusCode) throws Exception;
	
	public int countAll() throws Exception;
	
	public boolean deleteById (Integer lotId) throws Exception;
}
