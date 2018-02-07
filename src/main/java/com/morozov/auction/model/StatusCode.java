package com.morozov.auction.model;

import java.io.Serializable;


public class StatusCode implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer statusCode;

	private String name;

	public StatusCode() {
	}
	
	public StatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String s = "Status: " + getName();
		return s;
	}
	
	

}
