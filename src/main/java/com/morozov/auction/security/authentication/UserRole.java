package com.morozov.auction.security.authentication;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private String role;
	
	public UserRole(String role) {
		this.role = role;
	}
	
	@Override
	public String getAuthority() {		
		return role;
	}

}
