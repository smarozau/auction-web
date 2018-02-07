package com.morozov.auction.dao;

import com.morozov.auction.model.UserCredentials;

public interface UserCredentialsDao {
	
	public UserCredentials findByEmail(String email) throws Exception;
	
	public void save(UserCredentials userCredentials) throws Exception;

	public void confirm(int userId) throws Exception;

}
