package com.morozov.auction.dao.impl;

import java.math.BigDecimal;
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

import com.morozov.auction.dao.SteadDao;
import com.morozov.auction.dao.impl.UserDaoImpl.UserRowMapper;
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;

public class SteadDaoImpl implements SteadDao {

	protected final class SteadRowMapper implements RowMapper<Stead> {
		public Stead mapRow(ResultSet rs, int rowNum) throws SQLException {
			Stead stead = new Stead();
			User user = new User();
			stead.setSteadId(rs.getInt("STEAD_ID"));

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

			stead.setOwner(user);

			stead.setSteadCountry(rs.getString("STEAD_COUNTRY"));
			stead.setSteadRegion(rs.getString("STEAD_REGION"));
			stead.setSteadCity(rs.getString("STEAD_CITY"));
			stead.setSteadAddress(rs.getString("STEAD_ADDRESS"));
			stead.setCoordinates(rs.getString("COORDINATES"));
			stead.setSize(rs.getDouble("SIZE"));
			stead.setDescription(rs.getString("DESCRIPTION"));
			stead.setReservePrice(rs.getBigDecimal("RESERVE_PRICE"));
			stead.setCrtdTms(rs.getTimestamp("STEAD_CRTD_TMS"));
			stead.setUptdTms(rs.getTimestamp("STEAD_UPTD_TMS"));
			return stead;
		}
	}

	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	private Logger logger = LoggerFactory.getLogger(SteadDaoImpl.class);

	@Override
	public void save(Stead stead) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving stead " + stead);
		}
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("OWNER_ID", stead.getOwner().getUserId())
				.addValue("STEAD_COUNTRY", stead.getSteadCountry())
				.addValue("STEAD_REGION", stead.getSteadRegion())
				.addValue("STEAD_CITY", stead.getSteadCity())
				.addValue("STEAD_ADDRESS", stead.getSteadAddress())
				.addValue("COORDINATES", stead.getCoordinates())
				.addValue("SIZE", stead.getSize())
				.addValue("DESCRIPTION", stead.getDescription())
				.addValue("RESERVE_PRICE", stead.getReservePrice());

		String sql = null;

		if (stead.getSteadId() == null) {
			sql = "INSERT INTO STEAD "
					+ "(OWNER_ID, STEAD_COUNTRY, STEAD_REGION, STEAD_CITY, STEAD_ADDRESS, COORDINATES, SIZE, DESCRIPTION, "
					+ "RESERVE_PRICE) VALUES (:OWNER_ID,:STEAD_COUNTRY, :STEAD_REGION, :STEAD_CITY, :STEAD_ADDRESS, :COORDINATES,"
					+ " :SIZE, :DESCRIPTION, :RESERVE_PRICE)";

			KeyHolder genKeyHolder = new GeneratedKeyHolder();
			npJdbcTemplate.update(sql, params, genKeyHolder);
			Number key = genKeyHolder.getKey();
			stead.setSteadId(key.intValue());

		} else {
			params = new MapSqlParameterSource()
					.addValue("OWNER_ID", stead.getOwner().getUserId())
					.addValue("STEAD_COUNTRY", stead.getSteadCountry())
					.addValue("STEAD_REGION", stead.getSteadRegion())
					.addValue("STEAD_CITY", stead.getSteadCity())
					.addValue("STEAD_ADDRESS", stead.getSteadAddress())
					.addValue("COORDINATES", stead.getCoordinates())
					.addValue("SIZE", stead.getSize())
					.addValue("DESCRIPTION", stead.getDescription())
					.addValue("RESERVE_PRICE", stead.getReservePrice())
					.addValue("STEAD_ID", stead.getSteadId());
			sql = "UPDATE STEAD SET OWNER_ID=:OWNER_ID, STEAD_COUNTRY=:STEAD_COUNTRY, STEAD_REGION=:STEAD_REGION,"
					+ " STEAD_CITY=:STEAD_CITY, STEAD_ADDRESS=:STEAD_ADDRESS, COORDINATES=:COORDINATES,"
					+ "SIZE = :SIZE, DESCRIPTION = :DESCRIPTION, RESERVE_PRICE =:RESERVE_PRICE WHERE STEAD_ID=:STEAD_ID";
			npJdbcTemplate.update(sql, params);
		}
	}

	@Override
	public Stead findById(Integer steadId) throws Exception {
		String sql = "SELECT S.STEAD_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS "
				+ "FROM STEAD AS S INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID WHERE S.STEAD_ID=:STEAD_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STEAD_ID", steadId);
		try {
			return npJdbcTemplate.queryForObject(sql, params, new SteadRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Stead> findByCountry(String country) throws Exception {
		String sql = "SELECT S.STEAD_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ "  U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS "
				+ "FROM STEAD AS S INNER JOIN USER_PROFILE AS U ON S.OWNER_ID = U.USER_ID WHERE S.STEAD_COUNTRY=:STEAD_COUNTRY";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STEAD_COUNTRY", country);
		try {
			return npJdbcTemplate.query(sql, params, new SteadRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public List<Stead> findByRegion(String region) throws Exception {
		String sql = "SELECT S.STEAD_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ "  U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, U.CRTD_TMS, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS "
				+ "FROM STEAD AS S INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID WHERE S.STEAD_REGION=:STEAD_REGION";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STEAD_REGION", region);
		try {
			return npJdbcTemplate.query(sql, params, new SteadRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Stead> findByUserId(Integer userId) throws Exception {
		String sql = "SELECT S.STEAD_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ "  U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS "
				+ "FROM STEAD AS S INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID WHERE S.OWNER_ID=:OWNER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("OWNER_ID", userId);
		try {
			return npJdbcTemplate.query(sql, params, new SteadRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public List<Stead> findAvailableByUserId(Integer userId, Integer auctionId) throws Exception {
		String sql = "SELECT S.STEAD_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ "  U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS "
				+ "FROM STEAD AS S INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID WHERE S.OWNER_ID=:OWNER_ID"
				+ " AND S.STEAD_ID NOT IN (SELECT STEAD_ID FROM LOT WHERE AUCTION_ID=:AUCTION_ID)";
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("OWNER_ID", userId)
				.addValue("AUCTION_ID", auctionId);
		try {
			return npJdbcTemplate.query(sql, params, new SteadRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	

	@Override
	public List<Stead> findByCity(String city) throws Exception {
		String sql = "SELECT S.STEAD_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ "  U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS "
				+ "FROM STEAD AS S INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID WHERE S.STEAD_CITY=:STEAD_CITY";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STEAD_CITY", city);
		try {
			return npJdbcTemplate.query(sql, params, new SteadRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Stead> findByReservePrice(BigDecimal reservePrice) {
		String sql = "SELECT S.STEAD_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ "  U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS "
				+ "FROM STEAD AS S INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID WHERE S.RESERVE_PRICE<=:RESERVE_PRICE";
		SqlParameterSource params = new MapSqlParameterSource().addValue("RESERVE_PRICE", reservePrice);
		try {
			return npJdbcTemplate.query(sql, params, new SteadRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM STEAD";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	public boolean deleteById(Integer steadId) throws Exception {
		boolean result = false;
		String sql = "DELETE FROM STEAD WHERE STEAD_ID=:ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STEAD_ID", steadId);
		npJdbcTemplate.update(sql, params);
		result = true;
		return result;
	}

	@Override
	public List<Stead> findAll() throws Exception {
		String sql = "SELECT S.STEAD_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ "  U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS "
				+ "FROM STEAD AS S INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID";
		return npJdbcTemplate.query(sql, new SteadRowMapper());
	}

}
