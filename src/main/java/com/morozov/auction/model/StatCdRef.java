package com.morozov.auction.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stat_cd_ref database table.
 * 
 */
@Entity
@Table(name="stat_cd_ref")
@NamedQuery(name="StatCdRef.findAll", query="SELECT s FROM StatCdRef s")
public class StatCdRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STAT_CD")
	private int statCd;

	private String name;

	//bi-directional many-to-one association to Auction
	@OneToMany(mappedBy="statCdRef")
	private List<Auction> auctions;

	public StatCdRef() {
	}

	public int getStatCd() {
		return this.statCd;
	}

	public void setStatCd(int statCd) {
		this.statCd = statCd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Auction> getAuctions() {
		return this.auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public Auction addAuction(Auction auction) {
		getAuctions().add(auction);
		auction.setStatCdRef(this);

		return auction;
	}

	public Auction removeAuction(Auction auction) {
		getAuctions().remove(auction);
		auction.setStatCdRef(null);

		return auction;
	}

}