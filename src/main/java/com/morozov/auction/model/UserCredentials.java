package com.morozov.auction.model;

public class UserCredentials {
	
	private int userId;
	
	private String password;
	
	private byte[] encryptedPassword;
	
	private byte[] salt;
	
	public UserCredentials() {
		super();
	}
	
	public UserCredentials(int userId, String password, byte[] encryptedPassword, byte[] salt) {
		this();
		this.userId = userId;
		this.password = password;
		this.encryptedPassword = encryptedPassword;
		this.salt = salt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(byte[] encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	
	

}
