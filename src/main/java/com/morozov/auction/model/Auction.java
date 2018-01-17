package com.morozov.auction.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;


public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	private Date endTime;

	private Date startTime;

	private StatusCode statusCode;

	private List<Lot> lots;

	public Auction() {
	}
	
	public Auction(Date endTime, Date startTime, StatusCode statusCode) {
		
		this.endTime = endTime;
		this.startTime = startTime;
		this.statusCode = statusCode;
	}



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public StatusCode getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public List<Lot> getLots() {
		return this.lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}

	public String getFormattedTime(Date date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		return formatDate.format(date);
	}
	
	@Override
	public String toString() {
		return String.format("Auction {id=%d, start time=%s, end time=%s, %s, list of lots=%s}",
				id, startTime, endTime, statusCode, getLots());	
	}

}