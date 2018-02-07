package com.morozov.auction.dao;

import java.math.BigDecimal;
import java.util.List;

import com.morozov.auction.model.Stead;

/**
 * @author Sergey Morozov
 *
 */
public interface SteadDao {

    public void save(Stead stead) throws Exception;
    
    public List<Stead> findAll() throws Exception;
	
	public Stead findById(Integer steadId) throws Exception;
	
	public List<Stead> findByCountry (String country) throws Exception;
	
	public List<Stead> findByRegion (String region) throws Exception;
	
	public List<Stead> findByUserId(Integer userId) throws Exception;
	
	public List<Stead> findByCity(String city) throws Exception;
	
	public List<Stead> findByReservePrice(BigDecimal reservePrice);
	
	public int countAll() throws Exception;
	
	public boolean deleteById (Integer steadId) throws Exception;
}
