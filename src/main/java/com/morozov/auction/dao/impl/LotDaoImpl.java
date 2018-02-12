package com.morozov.auction.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.morozov.auction.dao.LotDao;
import com.morozov.auction.model.Auction;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.StatusCode;
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;

public class LotDaoImpl implements LotDao {

	protected final class LotRowMapper implements RowMapper<Lot> {
		public Lot mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lot lot = new Lot();
			Auction auction = new Auction();
			Stead stead = new Stead();
			StatusCode statusCode = new StatusCode();
			User user = new User();
			
			lot.setLotId(rs.getInt("LOT_ID"));
			
			auction.setId(rs.getInt("ID"));
			auction.setStartTime(rs.getDate("START_TIME"));
			auction.setEndTime(rs.getDate("END_TIME"));
			statusCode.setStatusCode(rs.getInt("STAT_CD"));
			statusCode.setName(rs.getString("NAME"));
			auction.setStatusCode(statusCode);
			lot.setAuction(auction);
			
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
			lot.setStead(stead);
						
			return lot;
		}
	}
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}
	
	private Logger logger = LoggerFactory.getLogger(LotDaoImpl.class);
	
	@Override
	public void save(Lot lot) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving lot " + lot);
		}
		SqlParameterSource params = new MapSqlParameterSource()

				.addValue("AUCTION_ID", lot.getAuction().getId())
				.addValue("STEAD_ID", lot.getStead().getSteadId());
		

		String sql = null;
		Auction auction = lot.getAuction();
		List<Lot> lots  = auction.getLots();

		if (lot.getLotId() == null) {
			sql = "INSERT INTO LOT "
					+ "(AUCTION_ID, STEAD_ID)"
					+ " VALUES (:AUCTION_ID, :STEAD_ID)";
			
			KeyHolder genKeyHolder = new GeneratedKeyHolder();
			npJdbcTemplate.update(sql, params, genKeyHolder);
			Number key = genKeyHolder.getKey();
			lot.setLotId(key.intValue());
			lots.add(lot);

		} else {
			params = new MapSqlParameterSource()
					.addValue("LOT_ID", lot.getLotId())
					.addValue("AUCTION_ID", lot.getAuction().getId())
					.addValue("STEAD_ID", lot.getStead().getSteadId());
			sql = "UPDATE LOT SET AUCTION_ID=:AUCTION_ID, STEAD_ID=:STEAD_ID WHERE LOT_ID=:LOT_ID";
			npJdbcTemplate.update(sql, params);
		}
		}
	
	
	@Override
	public void saveBatch(List<Lot> lots) {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving lots " + lots);
		}
		String sql = "INSERT INTO LOT (AUCTION_ID, STEAD_ID) VALUES (:AUCTION_ID, :STEAD_ID)";
		List<Map<String, Object>> batchValues = new ArrayList<>(lots.size());
		for (Lot lot : lots) {
		    batchValues.add(
		            new MapSqlParameterSource()
		                    .addValue("AUCTION_ID", lot.getAuction().getId())
		                    .addValue("STEAD_ID", lot.getStead().getSteadId())
		                    .getValues());
		}
//		Map <String,Number>[] values = batchValues.toArray(new Map[lots.size()]);
		npJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[lots.size()]));
		
	}

	@Override
	public Lot findById(Integer lotId) throws Exception {
		String sql = "SELECT L.LOT_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_ID, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS, "
				+ "A.ID, A.START_TIME, A.END_TIME, SC.STAT_CD, SC.NAME FROM ((((LOT AS L INNER JOIN STEAD AS S ON L.STEAD_ID=S.STEAD_ID)"
				+ " INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID)"
				+ "INNER JOIN AUCTION AS A ON L.AUCTION_ID=A.ID) INNER JOIN STAT_CD_REF AS SC ON A.STATUS_CODE=SC.STAT_CD) "
				+ "WHERE L.LOT_ID=:LOT_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("LOT_ID", lotId);
		return npJdbcTemplate.queryForObject(sql, params, new LotRowMapper());
	}

	@Override
	public List<Lot> findLotsByUserId(Integer userId) throws Exception {
		String sql = "SELECT L.LOT_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_ID, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS, "
				+ "A.ID, A.START_TIME, A.END_TIME, SC.STAT_CD, SC.NAME FROM ((((LOT AS L INNER JOIN STEAD AS S ON L.STEAD_ID=S.STEAD_ID)"
				+ " INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID)"
				+ "INNER JOIN AUCTION AS A ON L.AUCTION_ID=A.ID) INNER JOIN STAT_CD_REF AS SC ON A.STATUS_CODE=SC.STAT_CD) "
				+ "WHERE U.USER_ID=:USER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("USER_ID", userId);
		return npJdbcTemplate.query(sql, params, new LotRowMapper());
	}

	@Override
	public List<Lot> findByAuctionId(Integer auctionId) throws Exception {
		String sql = "SELECT L.LOT_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_ID, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS, "
				+ "A.ID, A.START_TIME, A.END_TIME, SC.STAT_CD, SC.NAME FROM ((((LOT AS L INNER JOIN STEAD AS S ON L.STEAD_ID=S.STEAD_ID)"
				+ " INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID)"
				+ "INNER JOIN AUCTION AS A ON L.AUCTION_ID=A.ID) INNER JOIN STAT_CD_REF AS SC ON A.STATUS_CODE=SC.STAT_CD) "
				+ "WHERE A.ID=:AUCTION_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("AUCTION_ID", auctionId);
		return npJdbcTemplate.query(sql, params, new LotRowMapper());
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM LOT";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	public boolean deleteById(Integer lotId) throws Exception {
		boolean result = false;
		String sql = "DELETE FROM LOT WHERE LOT_ID=:ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("ID", lotId);
		npJdbcTemplate.update(sql, params);
		result = true;
		return result;
	}

	@Override
	public List<Integer> findByAuctionIdAndStatusCode(Integer auctionId, Integer statusCode) throws Exception {
		String sql = "SELECT L.LOT_ID FROM LOT AS L INNER JOIN AUCTION AS A ON L.AUCTION_ID=A.ID "
				+"WHERE A.ID=36 AND A.STATUS_CODE=3";
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("AUCTION_ID", auctionId)
				.addValue("STATUS_CODE", statusCode);
		return npJdbcTemplate.queryForList(sql, params, Integer.class);
	}

}
