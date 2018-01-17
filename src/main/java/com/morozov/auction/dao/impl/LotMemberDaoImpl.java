package com.morozov.auction.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.morozov.auction.dao.LotMemberDao;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.model.User;

public class LotMemberDaoImpl implements LotMemberDao {

//	protected final class LotMemberRowMapper implements RowMapper<LotMember> {
//		public LotMember mapRow(ResultSet rs, int rowNum) throws SQLException {
//			LotMember member = new LotMember();
//			User user = new User();
//			Lot lot = new Lot();
//			Auction auction = new Auction();
//			StatusCode statusCode = new StatusCode();
//			Stead stead = new Stead();
//
//			user.setUserId(rs.getInt("USER_ID"));
//			user.setDisplayName(rs.getString("DISPLAY_NAME"));
//			user.setFirstName(rs.getString("FIRST_NAME"));
//			user.setLastName(rs.getString("LAST_NAME"));
//			user.setEmail(rs.getString("EMAIL"));
//			user.setPhone(rs.getString("PHONE"));
//			user.setAddress(rs.getString("ADDRESS"));
//			user.setCity(rs.getString("CITY"));
//			user.setCountry(rs.getString("COUNTRY"));
//			user.setCrtdTms(rs.getTimestamp("CRTD_TMS"));
//			user.setUptdTms(rs.getTimestamp("UPTD_TMS"));
//			member.setUser(user);
//
//			lot.setLotId(rs.getInt("LOT_ID"));
//
//			auction.setId(rs.getInt("ID"));
//			auction.setStartTime(rs.getDate("START_TIME"));
//			auction.setEndTime(rs.getDate("END_TIME"));
//			statusCode.setStatusCode(rs.getInt("STAT_CD"));
//			statusCode.setName(rs.getString("NAME"));
//			auction.setStatusCode(statusCode);
//			lot.setAuction(auction);
//
//			stead.setId(rs.getInt("ID"));
//			stead.setOwner(user);
//			stead.setSteadCountry(rs.getString("STEAD_COUNTRY"));
//			stead.setSteadRegion(rs.getString("STEAD_REGION"));
//			stead.setSteadCity(rs.getString("STEAD_CITY"));
//			stead.setSteadAddress(rs.getString("STEAD_ADDRESS"));
//			stead.setCoordinates(rs.getString("COORDINATES"));
//			stead.setSize(rs.getDouble("SIZE"));
//			stead.setDescription(rs.getString("DESCRIPTION"));
//			stead.setReservePrice(rs.getBigDecimal("RESERVE_PRICE"));
//			stead.setCrtdTms(rs.getTimestamp("STEAD_CRTD_TMS"));
//			stead.setUptdTms(rs.getTimestamp("STEAD_UPTD_TMS"));
//			lot.setStead(stead);
//
//			member.setLot(lot);
//			return member;
//
//		}
//	}

	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	private Logger logger = LoggerFactory.getLogger(LotMemberDaoImpl.class);
	
	@Override
	public void saveLotMember(LotMember member) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving member for lot " + member);
		}
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("LOT_ID", member.getLot().getLotId())
				.addValue("MEMBER_ID", member.getUser().getUserId())
				.addValue("DEPOSIT", member.getDeposit());
		
		String sql = "INSERT INTO LOT_MEMBER "
				+ "(LOT_ID, MEMBER_ID, DEPOSIT)"
				+ " VALUES (:LOT_ID, :MEMBER_ID, :DEPOSIT)";
		npJdbcTemplate.update(sql, params);
	}

	@Override
	public List<User> findByLotId(Integer lotId) throws Exception {
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE FROM LOT_MEMBER AS LM INNER JOIN USER_PROFILE AS U ON LM.MEMBER_ID = U.USER_ID "
				+ "WHERE LM.LOT_ID=:LOT_ID";
				SqlParameterSource params = new MapSqlParameterSource()
						.addValue("LOT_ID", lotId);
			return npJdbcTemplate.query(sql, params, new UserDaoImpl().new UserRowMapper());
	}
	
	@Override
	public List<Lot> findByLotMemberId(Integer lotMemberId) throws Exception {
		String sql = "SELECT L.LOT_ID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.ID, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS, "
				+ "A.ID, A.START_TIME, A.END_TIME, SC.STAT_CD, SC.NAME FROM (((((LOT_MEMBER AS LM INNER JOIN LOT AS L ON LM.LOT_ID=L.LOT_ID)"
				+ " INNER JOIN STEAD AS S ON L.STEAD_ID=S.ID)"
				+ " INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID)"
				+ "INNER JOIN AUCTION AS A ON L.AUCTION_ID=A.ID) INNER JOIN STAT_CD_REF AS SC ON A.STATUS_CODE=SC.STAT_CD) "
				+ "WHERE LM.MEMBER_ID=:MEMBER_ID";
				SqlParameterSource params = new MapSqlParameterSource()
						.addValue("MEMBER_ID", lotMemberId);
			return npJdbcTemplate.query(sql, params, new LotDaoImpl().new LotRowMapper());
	}

}
