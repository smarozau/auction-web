package com.morozov.auction.dao.test;

import static org.junit.Assert.assertEquals;
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
import com.morozov.auction.dao.LotDao;
import com.morozov.auction.dao.SteadDao;
import com.morozov.auction.dao.UserDao;
import com.morozov.auction.model.Auction;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.StatusCode;
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/dao-config.xml")
public class LotDaoImplTest {

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
	public void testSave() {
		try {
			Lot lot = new Lot();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = dateFormat.parse("2017-12-30 12:30:00");
			Date end = dateFormat.parse("2017-12-30 15:30:00");
			Auction auction = new Auction(start, end, new StatusCode(1));
			auctionDao.createAuction(auction);
			lot.setAuction(auction);

			User user = new User("Test first name", "Test last name", "DislayName", "ul.Nedostoevskogo, 32 - 23",
					"Mensk", "Belarus", "+375291111111", "test@mail.by");
			userDao.save(user);
			Stead stead = new Stead(user, "Беларусь", "Минская обл.", "аг.Семково", "Прибрежная ул., 11",
					"54.005075 27.445786", 500.0,
					"Участок на берегу воды,первая линия,свой пляж.Закрытый район, шлагбаум, 10 мин до центра Проспект Победителей.Все комуникации.Торг только на месте.Построим дом.Есть другие варианты возле воды.",
					new BigDecimal(90000));
			steadDao.save(stead);
			lot.setStead(stead);
			lotDao.save(lot);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindById() {
		try {
			Lot lot = lotDao.findById(11);
			assertEquals("Inconsistent id ", 11, lot.getLotId().intValue(), 0.01);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindByUserId() {
		try {
			List<Lot> lots = lotDao.findLotsByUserId(240);
			for (Lot lot : lots) {
				System.out.println(lot);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindByAuctionId() {
		try {
			List<Lot> lots = lotDao.findByAuctionId(1);
			for (Lot lot : lots) {
				System.out.println(lot);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
}
	
	@Test
	public void countAllTest() {
		try {
			int count = lotDao.countAll();
			System.out.println("Count of lots " + count);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void deleteByIdTest() {
		try {
			System.out.println("Lot id number 11 is deleted: " + lotDao.deleteById(11));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
