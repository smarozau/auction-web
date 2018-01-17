package com.morozov.auction.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class LotMember implements Serializable {
	private static final long serialVersionUID = 1L;

	private Lot lot;
	
	private BigDecimal deposit;
		
	private User user;

	public LotMember() {
	}
	
	public LotMember(User user, Lot lot) {
		this.user = user;
		this.lot = lot;
		
	}

	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit() {
		deposit = lot.getStead().getReservePrice().multiply( new BigDecimal(0.05));
	}

	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}
	
	@Override
	public String toString() {
		return String.format("Member for {%s, %s, deposit = %s}",
				getLot(), getUser(), getDeposit());	
	}

}