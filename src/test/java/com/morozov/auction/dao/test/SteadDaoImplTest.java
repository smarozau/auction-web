package com.morozov.auction.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.SteadDao;
import com.morozov.auction.dao.UserDao;
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-config.xml")
public class SteadDaoImplTest {

	@Autowired
	SteadDao steadDao;

	@Autowired
	UserDao userDao;

	@Test
	@Transactional
	@Rollback(true)
	public void saveTest() {
		try {
			User user = new User("Test first name", "Test last name", "TestName", "ul.Nedostoevskogo, 32 - 23", "Mensk",
					"Belarus", "+375291111111", "test@mail.by");
			userDao.save(user);
			Stead stead = new Stead("Беларусь", "Минская обл.", "аг.Семково", "Прибрежная ул., 11",
					"54.005075 27.445786", 500.0,
					"Участок на берегу воды,первая линия,свой пляж.Закрытый район, шлагбаум, 10 мин до центра Проспект Победителей.Все комуникации.Торг только на месте.Построим дом.Есть другие варианты возле воды.",
					new BigDecimal(90000));
			stead.setOwner(user);
			steadDao.save(stead);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findByIdTest() {
		try {
			Stead stead = steadDao.findById(3);
			assertEquals("Inconsistent stead id ", 3, stead.getSteadId().intValue(), 0.01);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findByCountryTest() {
		try {
			List<Stead> steads = steadDao.findByCountry("Беларусь");
			for (Stead stead : steads) {
				System.out.println(stead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	@Test
	@Transactional
	@Rollback(true)
	public void findByRegionTest() {
		try {
			List<Stead> steads = steadDao.findByRegion("Минская обл.");
			for (Stead stead : steads) {
				System.out.println(stead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findByUserIdTest() {
		try {
			List<Stead> steads = steadDao.findByUserId(240);
			for (Stead stead : steads) {
				System.out.println(stead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findByCityTest() {
		try {
			List<Stead> steads = steadDao.findByCity("аг.Семково");
			for (Stead stead : steads) {
				System.out.println(stead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findByReservePriceTest() {
		try {
			List<Stead> steads = steadDao.findByReservePrice(new BigDecimal(90000));
			for (Stead stead : steads) {
				System.out.println(stead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void countAllTest() {
		try {
			int count = steadDao.countAll();
			System.out.println("Count of steads " + count);
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
			System.out.println("Purchase orders number 32 is deleted: " + steadDao.findById(3));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findAll() {
		try {
			List<Stead> steads = steadDao.findAll();
			for (Stead stead : steads) {
				System.out.println(stead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
}
