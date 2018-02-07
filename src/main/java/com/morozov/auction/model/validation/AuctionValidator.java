package com.morozov.auction.model.validation;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.morozov.auction.model.Auction;

public class AuctionValidator implements Validator {
	
	private static final int ADDITION = 60 * 24;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Auction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Auction auction = (Auction) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "startTime.required", "Start time value is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "endTime.required", "Finish time value is required");
		         
		Date addDate = calculateAdditionalTime(ADDITION);
		if (auction.getStartTime().before(addDate))
			 errors.rejectValue("startTime", "startTime.wrongvalue", "Enter actual date");
		if (auction.getStartTime().after(auction.getEndTime()) || auction.getStartTime().compareTo(auction.getEndTime()) == 0)
			 errors.rejectValue("endTime", "endTime.wrongvalue", "Wrong time value");

	}
	
	private Date calculateAdditionalTime(int addTimeInMinutes) {
       	Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, addTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
