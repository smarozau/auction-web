package com.morozov.auction.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;



public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer bidId;

	private BigDecimal bid;

	private Timestamp crtdTms;

	private LotMember lotMember;

	public Bid() {
	}
	
	public Bid(LotMember lotMember, BigDecimal bid) {
		
		this.bid = bid;
		this.lotMember = lotMember;
		
	}

	public Integer getBidId() {
		return this.bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public BigDecimal getBid() {
		return this.bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public Timestamp getCrtdTms() {
		return this.crtdTms;
	}

	public void setCrtdTms(Timestamp crtdTms) {
		this.crtdTms = crtdTms;
	}

	public LotMember getLotMember() {
		return this.lotMember;
	}

	public void setLotMember(LotMember lotMember) {
		this.lotMember = lotMember;
	}

	@Override
	public String toString() {
		return String.format("Bid {id = %s, %s, bid = %s}",
				getBidId(), getLotMember(), getBid());	
	}
	
}