package com.morozov.auction.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date endTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startTime;

	private StatusCode statusCode;

	private List<Lot> lots;

	public Auction() {
	}
	
	public Auction(Date endTime, Date startTime) {
		
		this.endTime = endTime;
		this.startTime = startTime;
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
		if (this.lots == null) {
			this.lots = new ArrayList<Lot>();
		}
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