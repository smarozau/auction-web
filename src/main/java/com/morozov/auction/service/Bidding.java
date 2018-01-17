package com.morozov.auction.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;

import com.morozov.auction.model.Bid;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.service.impl.BidServiceImpl;


public class Bidding extends Thread {

	private LotMember client;
	
	BidService bidService = new BidServiceImpl();
	
	Lock locking = new ReentrantLock();

	public Bidding(LotMember client) {
		this.client = client;
	}

	public LotMember getClient() {
		return client;
	}

	public void run() {
		
		locking.lock();
		try {
				BigDecimal clientBid = (client.getLot().getStead().getReservePrice()).multiply(new BigDecimal(1.05));
				Bid bid = new Bid();
				bid.setBid(clientBid);
				bid.setLotMember(client);
				bidService.makeBid(bid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locking.unlock();
		}
	}

}
