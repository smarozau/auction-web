package com.morozov.auction.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Sergey Morozov
 * 
 */

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String address;

	private String city;

	private String country;

	private Timestamp crtdTms;

	private String displayName;

	private String firstName;

	private String lastName;

	private String phone;

	private String type;

	private Timestamp uptdTms;

	private String email;

	private List<Stead> steads;

	public User() {
	}

	public User(String firstName, String lastName, String displayName, String address, String city, String country,   
			String phone, String email) {
		this.address = address;
		this.city = city;
		this.country = country;
		this.displayName = displayName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUptdTms() {
		return this.uptdTms;
	}

	public void setUptdTms(Timestamp uptdTms) {
		this.uptdTms = uptdTms;
	}

	public List<Stead> getSteads() {
		return this.steads;
	}

	public void setSteads(List<Stead> steads) {
		this.steads = steads;
	}

	public List<Stead> addStead(Stead stead) {
		List<Stead> steads = getSteads();
		steads.add(stead);
		return steads;
	}

	public List<Stead> removeStead(Stead stead) {
		List<Stead> steads = getSteads();
		steads.remove(stead);
		return steads;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("{User ID: %s, First Name: %s, Last Name: %s, Country: %s, Email: %s, Phone: %s}", 
				getUserId(), getFirstName(), getLastName(), getCountry(), getEmail(), getPhone());
	}
	
	

}