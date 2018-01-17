package com.morozov.auction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.morozov.auction.dao.UserCredentialsDao;
import com.morozov.auction.model.UserCredentials;
import com.morozov.auction.service.UserCredentialsService;

public class UserCredentialsServiceImpl implements UserCredentialsService {
	
	@Autowired
	private UserCredentialsDao credentialsDao;

	@Override
	public UserCredentials findCredentialsByEmail(String email) throws Exception {		
		return credentialsDao.findByEmail(email);
	}

}
