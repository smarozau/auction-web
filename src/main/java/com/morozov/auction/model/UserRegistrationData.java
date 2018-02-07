package com.morozov.auction.model;

public class UserRegistrationData {
	
	private User user;
	
	private String password;
	
	private String confirmPassword;
	
	public UserRegistrationData() {		
	}
	
	public UserRegistrationData(User user) {
		setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
