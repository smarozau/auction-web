package com.morozov.auction.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.UserDao;
//import com.morozov.auction.filters.UserFilter;
import com.morozov.auction.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/dao-config.xml")
public class UserDaoImplTest {
	
	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		User user = new User("Test first name", "Test last name", "TestDislayName", "ul.Nedostoevskogo, 32 - 23", "Mensk", "Belarus",
				"+375291111111", "test@mail.by");
		try {
			userDao.save(user);			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test	
	public void testFindById() {
		try {
			User user = userDao.findById(41);
			assertEquals("Inconsistent id ", 41, user.getUserId().intValue(), 0.01);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindAll() {
		try {
			List<User> users = userDao.findAll();
			for(User user : users) {
				System.out.println(user);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test	
	public void testCountAll() {
		try {
			int count = userDao.countAll();
			System.out.println("Users count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
//	@Test	
//	public void testFindByFilter() {
//		try {
//			UserFilter filter = new UserFilter();
//			filter.setCount(0);
//			filter.setCount(10);
//			List<User> users = userDao.findByFilter(filter);
//			Assert.assertTrue("Number of users exceeds expected 10", users.size() <= 10);
//			System.out.println("Users count: " + users.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail(e.getMessage());
//		}
//	}

}
