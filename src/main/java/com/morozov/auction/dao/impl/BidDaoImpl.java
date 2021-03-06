package com.morozov.auction.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.BidDao;
import com.morozov.auction.model.Auction;
import com.morozov.auction.model.Bid;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.model.StatusCode;
import com.morozov.auction.model.Stead;
import com.morozov.auction.model.User;

public class BidDaoImpl implements BidDao {

	protected final class BidRowMapper implements RowMapper<Bid> {
	public Bid mapRow(ResultSet rs, int rowNum) throws SQLException {
		Bid bid = new Bid();
		User user = new User();
		Lot lot = new Lot();
		LotMember member = new LotMember();
		Auction auction = new Auction();
		StatusCode statusCode = new StatusCode();
		Stead stead = new Stead();
		
		bid.setBidId(rs.getInt("BID_ID"));
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
		member.setUser(user);

		lot.setLotId(rs.getInt("LOT_ID"));

		auction.setId(rs.getInt("ID"));
		auction.setStartTime(rs.getDate("START_TIME"));
		auction.setEndTime(rs.getDate("END_TIME"));
		statusCode.setStatusCode(rs.getInt("STAT_CD"));
		statusCode.setName(rs.getString("NAME"));
		auction.setStatusCode(statusCode);
		lot.setAuction(auction);

		stead.setSteadId(rs.getInt("STEAD_ID"));
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

		member.setLot(lot);
		member.setDeposit();
		bid.setLotMember(member);
		bid.setBid(rs.getBigDecimal("BID"));
		return bid;

	}
}
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	private Logger logger = LoggerFactory.getLogger(BidDaoImpl.class);
	
	@Override
	public void makeBid(Bid bid) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving bid " + bid);
		}
	
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("BIDDER_ID", bid.getLotMember().getUser().getUserId())
				.addValue("LOT_ID", bid.getLotMember().getLot().getLotId())
				.addValue("BID", bid.getBid());
		
		String sql = "INSERT INTO BID "
				+ "(BIDDER_ID, LOT_ID, BID)"
				+ " VALUES (:BIDDER_ID, :LOT_ID, :BID)";
		KeyHolder genKeyHolder = new GeneratedKeyHolder(); 
		npJdbcTemplate.update(sql, params,genKeyHolder);
		Number key = genKeyHolder.getKey();
		bid.setBidId( key.intValue() );
	
	}

	

	@Override
	public List<Bid> findBidsByLotId(Integer lotId) throws Exception {
		String sql = "SELECT B.BID_ID, B.LOT_ID, B.BID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_ID, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS, "
				+ "A.ID, A.START_TIME, A.END_TIME, SC.STAT_CD, SC.NAME FROM (((((BID AS B INNER JOIN LOT AS L ON B.LOT_ID=L.LOT_ID)"
				+ " INNER JOIN STEAD AS S ON L.STEAD_ID=S.STEAD_ID)"
				+ " INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID)"
				+ "INNER JOIN AUCTION AS A ON L.AUCTION_ID=A.ID) INNER JOIN STAT_CD_REF AS SC ON A.STATUS_CODE=SC.STAT_CD) "
				+ "WHERE L.LOT_ID=:LOT_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("LOT_ID", lotId);
		return npJdbcTemplate.query(sql, params, new BidRowMapper());
	}
	
	@Override
	public List<Bid> findBidsByUserId(Integer userId) throws Exception {
		String sql = "SELECT B.BID_ID, B.LOT_ID, B.BID, U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.DISPLAY_NAME, U.EMAIL, U.CRTD_TMS, U.UPTD_TMS,"
				+ " U.COUNTRY, U.CITY, U.ADDRESS, U.PHONE, S.STEAD_ID, S.STEAD_COUNTRY, S.STEAD_REGION, S.STEAD_CITY, "
				+ "S.STEAD_ADDRESS, S.COORDINATES, S.SIZE, S.DESCRIPTION, S.RESERVE_PRICE, S.STEAD_CRTD_TMS, S.STEAD_UPTD_TMS, "
				+ "A.ID, A.START_TIME, A.END_TIME, SC.STAT_CD, SC.NAME FROM (((((BID AS B INNER JOIN LOT AS L ON B.LOT_ID=L.LOT_ID)"
				+ " INNER JOIN STEAD AS S ON L.STEAD_ID=S.STEAD_ID)"
				+ " INNER JOIN USER_PROFILE AS U ON S.OWNER_ID=U.USER_ID)"
				+ "INNER JOIN AUCTION AS A ON L.AUCTION_ID=A.ID) INNER JOIN STAT_CD_REF AS SC ON A.STATUS_CODE=SC.STAT_CD) "
				+ "WHERE B.BIDDER_ID=:USER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("USER_ID", userId);
		return npJdbcTemplate.query(sql, params, new BidRowMapper());
	}

	@Override
	public BigDecimal findMaxBidForLot(Integer lotId) throws Exception {
		String sql = "SELECT max(BID) FROM BID WHERE LOT_ID=:LOT_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("LOT_ID", lotId);
		return npJdbcTemplate.queryForObject(sql, params, BigDecimal.class);
	}

	@Override
	public Integer findBidderIdForLotByBid(BigDecimal maxBid, Integer lotId) throws Exception {
		String sql = "SELECT BIDDER_ID FROM BID WHERE BID=:BID AND LOT_ID=:LOT_ID";
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("BID", maxBid)
				.addValue("LOT_ID", lotId);
		
		return npJdbcTemplate.queryForObject(sql, params, Integer.class);
	}


	@Override
	@Transactional
	public void updateLotForWinner(Bid bid) throws Exception {
		String sql = "UPDATE STEAD SET OWNER_ID=:USER_ID, S.RESERVE_PRICE:=BID WHERE STEAD_ID=:STEAD_ID";
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("OWNER_ID", bid.getLotMember().getUser().getUserId())
				.addValue("LOT_ID", bid.getLotMember().getLot().getLotId())
				.addValue("RESERVE_PRICE", bid.getBid())
				.addValue("STEAD_ID", bid.getLotMember().getLot().getStead().getSteadId());
		npJdbcTemplate.update(sql, params);
				
	}

}
