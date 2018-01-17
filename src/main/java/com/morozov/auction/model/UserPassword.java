package com.morozov.auction.model;

import java.io.Serializable;

public class UserPassword implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int userId;

	private String accepted;

	
	private byte[] passwordHash;

	
	private byte[] passwordSalt;

	public UserPassword() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccepted() {
		return this.accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public byte[] getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(byte[] passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

}