package com.morozov.auction.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

import com.morozov.auction.dao.UserDao;
import com.morozov.auction.model.User;

public class UserDaoImpl implements UserDao {

	protected final class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getInt("USER_ID"));
			user.setDisplayName(rs.getString("DISPLAY_NAME"));
			user.setFirstName(rs.getString("FIRST_NAME"));
			user.setLastName(rs.getString("LAST_NAME"));
			user.setEmail(rs.getString("EMAIL"));
			user.setPhone(rs.getString("PHONE"));
			user.setAddress(rs.getString("ADDRESS"));
			user.setCity(rs.getString("CITY"));
			user.setCountry(rs.getString("COUNTRY"));
			user.setCrtdTms(rs.getTimestamp("CRTD_TMS"));
			user.setUptdTms(rs.getTimestamp("UPTD_TMS"));
			return user;
		}
	}
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}
	
	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public void save(User user) throws Exception {
		if ( logger.isDebugEnabled() ) {
			logger.debug("saving user " + user);
		}
			SqlParameterSource params = new MapSqlParameterSource()
					.addValue("USER_ID", user.getUserId())
					.addValue("DISPLAY_NAME", user.getDisplayName())
					.addValue("FIRST_NAME", user.getFirstName())
					.addValue("LAST_NAME", user.getLastName())
					.addValue("EMAIL", user.getEmail())
					.addValue("PHONE", user.getPhone())
					.addValue("ADDRESS", user.getAddress())
					.addValue("CITY", user.getCity())
					.addValue("COUNTRY", user.getCountry());
			
			String sql = null;
			
			if (user.getUserId() == null) {			
				sql = "INSERT INTO USER_PROFILE "
						+ "(DISPLAY_NAME, FIRST_NAME, LAST_NAME, EMAIL, PHONE, ADDRESS, CITY, COUNTRY)"
						+ " VALUES (:DISPLAY_NAME,:FIRST_NAME, :LAST_NAME, :EMAIL, :PHONE, :ADDRESS, :CITY, :COUNTRY)";
				
				KeyHolder genKeyHolder = new GeneratedKeyHolder(); 
				npJdbcTemplate.update(sql, params, genKeyHolder);
				Number key = genKeyHolder.getKey();
				user.setUserId( key.intValue() );
				
			} else {	
				sql = "UPDATE USER_PROFILE SET DISPLAY_NAME=:DISPLAY_NAME, FIRST_NAME=:FIRST_NAME,"
						+ " LAST_NAME=:LAST_NAME, EMAIL=:EMAIL, PHONE=:PHONE,"
						+ "ADDRESS = :ADDRESS, CITY =:CITY, COUNTRY = :COUNTRY WHERE USER_ID=:USER_ID";
				npJdbcTemplate.update(sql, params);
			}
		}
		
	

	@Override
	public User findById(int userId) throws Exception {
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE FROM USER_PROFILE AS U WHERE USER_ID=:USER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("USER_ID", userId);
		try {
			return npJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() throws Exception {
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE FROM USER_PROFILE AS U";
				
			return npJdbcTemplate.query(sql, new UserRowMapper());
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM USER_PROFILE AS U ";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	public User findByEmail(String email) {
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE FROM USER_PROFILE AS U WHERE U.EMAIL=:EMAIL";
		   SqlParameterSource params = new MapSqlParameterSource().addValue("EMAIL", email);
		try {		
			return npJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public User findByDisplayName(String displayName) throws Exception {
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE FROM USER_PROFILE AS U WHERE U.DISPLAY_NAME=:DISPLAY_NAME";
		   SqlParameterSource params = new MapSqlParameterSource().addValue("EMAIL", displayName);
		try {		
			return npJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
