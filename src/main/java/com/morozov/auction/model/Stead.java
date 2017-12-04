package com.morozov.auction.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the stead database table.
 * 
 */
@Entity
@NamedQuery(name="Stead.findAll", query="SELECT s FROM Stead s")
public class Stead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String country;
	
	private String city;
	
	private String address;

	private Object coordinates;

	@Column(name="CRTD_TMS")
	private Timestamp crtdTms;

	private String description;

	@Column(name="RESERVE_PRICE")
	private BigDecimal reservePrice;

	@Column(name="UPTD_TMS")
	private Timestamp uptdTms;

	public Stead() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Object getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(Object coordinates) {
		this.coordinates = coordinates;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCrtdTms() {
		return this.crtdTms;
	}

	public void setCrtdTms(Timestamp crtdTms) {
		this.crtdTms = crtdTms;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getReservePrice() {
		return this.reservePrice;
	}

	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}

	public Timestamp getUptdTms() {
		return this.uptdTms;
	}

	public void setUptdTms(Timestamp uptdTms) {
		this.uptdTms = uptdTms;
	}

}