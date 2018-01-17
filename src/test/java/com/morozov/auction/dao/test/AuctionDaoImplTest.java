package com.morozov.auction.dao.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.AuctionDao;
import com.morozov.auction.model.Auction;
import com.morozov.auction.model.StatusCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-config.xml")
public class AuctionDaoImplTest {

	@Autowired
	private AuctionDao auctionDao;

	@Test
	@Transactional
	@Rollback(true)
	public void testCreateAuction() {
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = dateFormat.parse("2017-12-30 12:30:00");
			Date end = dateFormat.parse("2017-12-30 15:30:00");
			Auction auction = new Auction(start, end, new StatusCode(1));
			auctionDao.createAuction(auction);
			System.out.println("Auction id: " + auction.getId());
//			assertEquals("Not same dates: ", auction.getStartTime(), start);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindById() {
		try {
			Auction auction = auctionDao.findById(1);
			assertEquals("Inconsistent id of auction ", 1, auction.getId().intValue(), 0.01);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testFindAll() {
		try {
			List<Auction> auctions = auctionDao.findAll();
			for(Auction auction : auctions) {
				System.out.println(auction);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testFindByStatusCode() {
		try{
			List<Auction> auctions = auctionDao.findByStatusCode(1);
			for(Auction auction : auctions) {
				System.out.println(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCounAll() {
		try {
			int count = auctionDao.countAll();
			System.out.println("Auction's counts: " + count);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
