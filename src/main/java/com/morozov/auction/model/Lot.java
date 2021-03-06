package com.morozov.auction.model;

import java.io.Serializable;
import java.util.List;




public class Lot {
	
	private Integer lotId;

	private Stead stead;
	
	private Auction auction;

	private List<LotMember> lotMembers;
	
	private List<Bid> bids;

	public Lot() {
	}

	public Lot(Auction auction, Stead stead) {
		this.auction = auction;
		this.stead = stead;
	}

	public Integer getLotId() {
		return this.lotId;
	}

	public void setLotId(Integer lotId) {
		this.lotId = lotId;
	}

	public Stead getStead() {
		return this.stead;
	}

	public void setStead(Stead stead) {
		this.stead = stead;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public List<LotMember> getLotMembers() {
		return this.lotMembers;
	}

	public void setLotMembers(List<LotMember> lotMembers) {
		this.lotMembers = lotMembers;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	@Override
	public String toString() {
		return String.format("Lot {id=%d, %s, list of lot's members=%s}",
				getLotId(), getStead(), getLotMembers());	
	}

}