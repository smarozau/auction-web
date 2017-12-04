package com.morozov.auction.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.UserDao;
import com.morozov.auction.model.User;
import com.morozov.auction.service.UserService;

public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	@Transactional
	public void save (User user) throws Exception {
		
		userDao.save(user);
	}

	@Override
	public User findById(int userId) throws Exception {
		
		return userDao.findById(userId);
	}

	@Override
	public List<User> findAll() throws Exception {
		
		return userDao.findAll();
	}

	@Override
	public int countAll() throws Exception {
		
		return userDao.countAll();
	}

	@Override
	public User findByEmail(String email) {
		
		return userDao.findByEmail(email);
	}

}
