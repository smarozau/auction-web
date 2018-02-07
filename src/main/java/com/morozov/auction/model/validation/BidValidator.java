package com.morozov.auction.model.validation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.morozov.auction.model.Bid;
import com.morozov.auction.model.Lot;
import com.morozov.auction.service.BidService;

public class BidValidator implements Validator {

	@Autowired
	private BidService bidService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Bid.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bid bid = (Bid) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bid", "bid.NotEmpty");

		Lot lot = bid.getLotMember().getLot();
		List<Bid> lotBids;
		try {
			lotBids = bidService.findBidsByLotId(lot.getLotId());

			if (!lotBids.isEmpty()) {
				Bid lastBid = lotBids.get(lotBids.size() - 1);
				BigDecimal last = lastBid.getBid();
				rejectBid(bid, last, errors);
			} else {
				BigDecimal reservePrice = lot.getStead().getReservePrice();
				rejectBid(bid, reservePrice, errors);
			}
		} catch (Exception e) {
			errors.rejectValue("bid", "bid.noCheck");
		}
	}

	private void rejectBid(Bid bid, BigDecimal lastBid, Errors errors) {
		if (bid.getBid().compareTo(lastBid) == -1 || bid.getBid().compareTo(lastBid) == 0) {
			errors.rejectValue("bid", "bid.lessThanNecessary", "Bid value must be greater than actual price price of item");
		} else if (bid.getBid().signum() == -1) {
			errors.rejectValue("bid", "bid.negativeValue", "Negative value of bid");
		} else if (bid.getBid().signum() == 0) {
			errors.rejectValue("bid", "bid.zero", "Bid value cannot be zero");
		}
	}

}
