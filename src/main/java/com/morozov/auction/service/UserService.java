package com.morozov.auction.service;

import java.util.List;

import com.morozov.auction.model.User;

/**
 * @author Sergey Morozov
 *
 */

public interface UserService {

		
		public void save(User user) throws Exception;
		
		public User findById(int userId) throws Exception;
		
		public List<User> findAll() throws Exception;
		
//		public List<User> findByFilter(UserFilter filter);
		
		public int countAll() throws Exception;
		
//		public int countByFilter(UserFilter filter) throws Exception;

		public User findByEmail(String email);

	}
