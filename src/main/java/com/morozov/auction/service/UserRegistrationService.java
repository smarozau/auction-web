package com.morozov.auction.service;

import com.morozov.auction.model.UserRegistrationData;

public interface UserRegistrationService {

	void register(UserRegistrationData registration) throws Exception;
	
	void confirm(String verificationToken) throws Exception;

}