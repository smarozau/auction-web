package com.morozov.auction.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.morozov.auction.dao.UserDao;
import com.morozov.auction.model.User;

@Repository
public class UserDaoJpaImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(UserDaoJpaImpl.class);
	
	@Override
	public void save(User user) throws Exception {
		if ( logger.isDebugEnabled() ) {
			logger.debug("Saving user " + user);
		}
		entityManager.persist(user);		
	}

	@Override
	public User findById(int userId) throws Exception {
		User user = entityManager.find(User.class, new Integer (userId));
		return user;
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> users = entityManager.createQuery("SELECT u FROM USER u", User.class)
				.getResultList();
		return users;
	}

	@Override
	public int countAll() throws Exception {
		Query query = entityManager.createQuery ("SELECT count(*) FROM USER");
		int count = (int) query.getSingleResult();
		return count;
	}

	@Override
	public User findByEmail(String email) {
		User user = entityManager.createQuery(
				"SELECT u FROM USER u WHERE u.email:=email", User.class)
				.setParameter("email", email).getSingleResult();
		return user;
	}

}
