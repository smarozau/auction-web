package com.morozov.auction.service;

import com.morozov.auction.model.UserCredentials;

public interface UserCredentialsService {
	
	public UserCredentials findCredentialsByEmail(String email) throws Exception;

}
