package com.morozov.auction.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the bid database table.
 * 
 */
@Entity
@NamedQuery(name="Bid.findAll", query="SELECT b FROM Bid b")
public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BID_ID")
	private int bidId;

	private BigDecimal bid;

	@Column(name="CRTD_TMS")
	private Timestamp crtdTms;

	//bi-directional many-to-one association to LotMember
	@ManyToOne
	@JoinColumn(name="BIDDER_ID")
	private LotMember lotMember;

	//bi-directional many-to-one association to Lot
	@ManyToOne
	@JoinColumn(name="LOT_ID")
	private Lot lot;


	public Bid() {
	}
	
	public Bid(int bidId, BigDecimal bid, LotMember lotMember, Lot lot) {
		super();
		this.bidId = bidId;
		this.bid = bid;
		this.lotMember = lotMember;
		this.lot = lot;
	}

	public int getBidId() {
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

	public Lot getLot() {
		return this.lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

}