package com.morozov.auction.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.morozov.auction.dao.VerificationTokenDao;
import com.morozov.auction.model.VerificationToken;



public class VerificationTokenDaoImpl implements VerificationTokenDao {

	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;	
	
	@Override
	public boolean deleteVerificationToken(String verificationToken) throws Exception {
		String sql = "DELETE FROM USER_VERIFICATION_TOKEN WHERE TOKEN = :TOKEN";
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("TOKEN", verificationToken);
		
		int deleted = npJdbcTemplate.update(sql, params);
		return (deleted == 1);
	}

	@Override
	public Integer findUserIdByVerificationToken(String verificationToken) throws Exception {
		String sql = "SELECT USER_ID FROM USER_VERIFICATION_TOKEN WHERE TOKEN = :TOKEN";
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("TOKEN", verificationToken);
		return npJdbcTemplate.queryForObject(sql, params, Integer.class);
	}

	@Override
	public void save(VerificationToken verificationToken) {
		if ( logger.isDebugEnabled() ) {
			logger.debug("saving verification token " + verificationToken);
		}
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("USER_ID", verificationToken.getUserId())
				.addValue("TOKEN", verificationToken.getToken())
				.addValue("EXPIRY_TIME", verificationToken.getExpiryTime());
		
		String sql = "INSERT INTO USER_VERIFICATION_TOKEN (USER_ID, TOKEN, EXPIRY_TIME) VALUES (:USER_ID, :TOKEN, :EXPIRY_TIME)";		 
		npJdbcTemplate.update(sql, params);
	}

}
