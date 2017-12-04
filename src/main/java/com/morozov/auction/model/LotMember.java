package com.morozov.auction.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the lot_member database table.
 * 
 */
@Entity
@Table(name="lot_member")
@NamedQuery(name="LotMember.findAll", query="SELECT l FROM LotMember l")
public class LotMember implements Serializable {
	private static final long serialVersionUID = 1L;

	private Lot lot;
	
	private BigDecimal deposit;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private User user;

	public LotMember() {
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

	public void setLotId(Lot lot) {
		this.lot = lot;
	}

}