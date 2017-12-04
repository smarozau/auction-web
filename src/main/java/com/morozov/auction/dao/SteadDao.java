package com.morozov.auction.dao;

import java.util.List;

import com.morozov.auction.model.Stead;

/**
 * @author Sergey Morozov
 *
 */
public interface SteadDao {

    public void save(Stead stead) throws Exception;
	
	public Stead findById(Integer steadId) throws Exception;
	
	public List<Stead> findSteadsByUserId(Integer userId) throws Exception;
	
	public List<Stead> findByCity(String city) throws Exception;
	
	public List<Stead> findByAuctionId(Integer auctionId) throws Exception;
	
	public int countAll() throws Exception;
	
	public boolean deleteById (Integer id) throws Exception;
}
