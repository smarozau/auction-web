package com.morozov.auction.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class VerificationToken {
	
	private static final int EXPIRATION = 60 * 24;
	 
	private Integer userId;
	
    private String token;
   
    private Date expiryTime;
    
    public VerificationToken(Integer userId) {
    	this.userId = userId;
    	this.expiryTime = calculateExpiryTime(EXPIRATION);
    	this.token = generateToken();
    }

	private Date calculateExpiryTime(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
	
	private String generateToken() {
		return UUID.randomUUID().toString();
	}
    
    public Integer getUserId() {
		return userId;
	}

	public String getToken() {
		return token;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}
    
    
}
