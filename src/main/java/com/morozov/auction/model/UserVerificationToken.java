package com.morozov.auction.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_verification_token database table.
 * 
 */
@Entity
@Table(name="user_verification_token")
@NamedQuery(name="UserVerificationToken.findAll", query="SELECT u FROM UserVerificationToken u")
public class UserVerificationToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private int userId;

	@Column(name="EXPIRY_TIME")
	private Timestamp expiryTime;

	private String token;

	public UserVerificationToken() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getExpiryTime() {
		return this.expiryTime;
	}

	public void setExpiryTime(Timestamp expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}