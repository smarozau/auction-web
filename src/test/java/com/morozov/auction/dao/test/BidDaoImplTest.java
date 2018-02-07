package com.morozov.auction.dao.test;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.DateFormat;
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
import com.morozov.auction.dao.BidDao;
import com.morozov.auction.dao.LotDao;
import com.morozov.auction.dao.LotMemberDao;
import com.morozov.auction.dao.SteadDao;
import com.morozov.auction.dao.UserDao;
import com.morozov.auction.model.Auction;
import com.morozov.auction.model.Bid;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.model.StatusCode;
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/dao-config.xml")
public class BidDaoImplTest {

	@Autowired
	private BidDao bidDao; 
	
	@Autowired
	private LotMemberDao memberDao; 
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LotDao lotDao; 

	@Autowired
	private AuctionDao auctionDao;

	@Autowired
	private SteadDao steadDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void makeBidTest() {
		try {
			LotMember member = new LotMember();
			Lot lot = new Lot();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = dateFormat.parse("2017-12-30 12:30:00");
			Date end = dateFormat.parse("2017-12-30 15:30:00");
			Auction auction = new Auction(start, end);
			auctionDao.createAuction(auction);
			lot.setAuction(auction);

			User user = new User("Test first name", "Test last name", "DislayName", "ul.Nedostoevskogo, 32 - 23",
					"Mensk", "Belarus", "+375291111111", "test@mail.by");
			userDao.save(user);
			Stead stead = new Stead("Беларусь", "Минская обл.", "аг.Семково", "Прибрежная ул., 11",
					"54.005075 27.445786", 500.0,
					"Участок на берегу воды,первая линия,свой пляж.Закрытый район, шлагбаум, 10 мин до центра Проспект Победителей.Все комуникации.Торг только на месте.Построим дом.Есть другие варианты возле воды.",
					new BigDecimal(90000));
			stead.setOwner(user);
			steadDao.save(stead);
			lot.setStead(stead);
			lotDao.save(lot);
			member.setLot(lot);
			member.setUser(user);
			member.setDeposit();
			memberDao.saveLotMember(member);
			Bid bid = new Bid();
			bid.setLotMember(member);
			bid.setBid(new BigDecimal(90100));
			bidDao.makeBid(bid);
			System.out.println("Bid id " + bid.getBidId() + ": " + bid.getBid() );
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findBidsByLotId() {
		try {
			List<Bid> bids = bidDao.findBidsByLotId(11);
			for (Bid bid : bids) {
				System.out.println(bid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findMaxBidForLot() {
		try {
			Bid bid = bidDao.findMaxBidForLot(11);
			System.out.println(bid);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
