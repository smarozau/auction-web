package com.morozov.auction.model.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.morozov.auction.model.LotMember;
import com.morozov.auction.model.User;
import com.morozov.auction.service.LotMemberService;

public class LotMemberValidator implements Validator {

	@Autowired
	private LotMemberService lotMemberService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LotMember.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LotMember lotMember = (LotMember) target;
		rejectIfLotMemberExist(lotMember, errors);

	}

	private void rejectIfLotMemberExist(LotMember lotMember, Errors errors) {
		User user = lotMember.getUser();
		
		try {
			List<User> users = lotMemberService.findByLotId(lotMember.getLot().getLotId());
			if (users.contains(user)) {
				errors.rejectValue("lotMember", "lotMember.exists", "You already are taking part of auction");
			}
		} catch(Exception e) {
			errors.rejectValue("lotMember", "lotMember.exists.nocheck", "Failed to verify member of auction");
		}
	}

}
