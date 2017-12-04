package com.morozov.auction.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_password database table.
 * 
 */
@Entity
@Table(name="user_password")
@NamedQuery(name="UserPassword.findAll", query="SELECT u FROM UserPassword u")
public class UserPassword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private int userId;

	private String accepted;

	@Column(name="PASSWORD_HASH")
	private byte[] passwordHash;

	@Column(name="PASSWORD_SALT")
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