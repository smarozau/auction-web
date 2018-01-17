package com.morozov.auction.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.morozov.auction.dao.UserCredentialsDao;
import com.morozov.auction.model.UserCredentials;



public class UserCredentialsDaoImpl implements UserCredentialsDao {
	
	protected final class UserCredentialsRowMapper implements RowMapper<UserCredentials> {
		public UserCredentials mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserCredentials userPassword = new UserCredentials();
			userPassword.setUserId( rs.getInt("USER_ID") );
			userPassword.setEncryptedPassword( rs.getBytes("PASSWORD_HASH") );
			userPassword.setSalt( rs.getBytes("PASSWORD_SALT") );			
			return userPassword;
		}
	}
	
	private Logger logger = LoggerFactory.getLogger(UserCredentialsDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	@Override
	public UserCredentials findByEmail(final String email) throws Exception {
		if (logger.isTraceEnabled() ) {
			logger.trace("Looking for user password for " + email);
		}
		
		final String sql = 
			"SELECT UP.USER_ID, UP.PASSWORD_HASH, UP.PASSWORD_SALT "
				+ " FROM USER_PASSWORD AS UP INNER JOIN USER_PROFILE AS U ON U.USER_ID=UP.USER_ID WHERE U.EMAIL=:EMAIL";
		final SqlParameterSource params = new MapSqlParameterSource().addValue("EMAIL", email);
		try {
			return npJdbcTemplate.queryForObject(sql, params, new UserCredentialsRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}		
	}

	@Override
	public void save(UserCredentials userPassword) throws Exception {
		if ( logger.isDebugEnabled() ) {
			logger.debug("saving user password " + userPassword.getUserId());
		}
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("PASSWORD_HASH", userPassword.getEncryptedPassword())
				.addValue("PASSWORD_SALT", userPassword.getSalt());
		
		
		String sql = 
			"INSERT INTO "
				+ " USER_PASSWORD (USER_ID, PASSWORD_HASH, PASSWORD_SALT) "
				+ 	" VALUES (:USER_ID, :PASSWORD_HASH, :PASSWORD_SALT) "
				+ " ON DUPLICATE KEY UPDATE PASSWORD_HASH = :PASSWORD_HASH, PASSWORD_SALT = :PASSWORD_SALT";
				
		KeyHolder genKeyHolder = new GeneratedKeyHolder(); 
		npJdbcTemplate.update(sql, params, genKeyHolder);
		Number key = genKeyHolder.getKey();
		userPassword.setUserId( key.intValue() );
	}	

}
