package com.morozov.auction.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;



public class Stead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer steadId;
	
	private User owner;

	private String steadCountry;
	
	private String steadRegion;
	
	private String steadCity;
	
	private String steadAddress;

	private String coordinates;
	
	private Double size;

	private Timestamp crtdTms;

	private String description;

	private BigDecimal reservePrice;

	private Timestamp uptdTms;

	public Stead() {
	}

	public Stead(String steadCountry, String steadRegion, String steadCity, String steadAddress,
			String coordinates, Double size, String description, BigDecimal reservePrice) {
		super();
		
		this.steadCountry = steadCountry;
		this.steadRegion = steadRegion;
		this.steadCity = steadCity;
		this.steadAddress = steadAddress;
		this.coordinates = coordinates;
		this.size = size;
		this.description = description;
		this.reservePrice = reservePrice;
	}

	public Integer getSteadId() {
		return this.steadId;
	}

	public void setSteadId(Integer steadId) {
		this.steadId = steadId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getSteadAddress() {
		return this.steadAddress;
	}

	public void setSteadAddress(String steadAddress) {
		this.steadAddress = steadAddress;
	}

	public String getSteadCity() {
		return this.steadCity;
	}

	public void setSteadCity(String steadCity) {
		this.steadCity = steadCity;
	}

	public String getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getSteadCountry() {
		return this.steadCountry;
	}

	public void setSteadCountry(String steadCountry) {
		this.steadCountry = steadCountry;
	}

	public String getSteadRegion() {
		return steadRegion;
	}

	public void setSteadRegion(String steadRegion) {
		this.steadRegion = steadRegion;
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

	@Override
	public String toString() {
		return String.format("Stead {id=%d, %s, country=%s, region=%s, city=%s, address=%s, coordinates=%s, size=%s, reserve price=%f}",
				getSteadId(), getOwner(), getSteadCountry(), getSteadRegion(), getSteadCity(), getSteadAddress(), 
				getCoordinates(), getSize(), getReservePrice());	
	}

}