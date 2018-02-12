package com.morozov.auction.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.morozov.auction.dao.AuctionDao;
import com.morozov.auction.model.Auction;
import com.morozov.auction.model.StatusCode;

public class AuctionDaoImpl implements AuctionDao {

	protected final class AuctionRowMapper implements RowMapper<Auction> {
		public Auction mapRow(ResultSet rs, int rowNum) throws SQLException {
			Auction auction = new Auction();
			StatusCode statusCode = new StatusCode();
			auction.setId(rs.getInt("ID"));
			auction.setStartTime(new Date(rs.getTimestamp("START_TIME").getTime()));
			auction.setEndTime(new Date(rs.getTimestamp("END_TIME").getTime()));

			statusCode.setStatusCode(rs.getInt("STAT_CD"));
			statusCode.setName(rs.getString("NAME"));
			auction.setStatusCode(statusCode);

			return auction;
		}
	}

	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	private Logger logger = LoggerFactory.getLogger(AuctionDaoImpl.class);

	@Override
	public void createAuction(Auction auction) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Creating new auction " + auction);
		}

			String auctionSql = null;
//			String lotsSql = null;
			if (auction.getId() == null) {
				auctionSql = "INSERT INTO AUCTION(START_TIME, END_TIME) "
						+ "VALUES (:START_TIME, :END_TIME)";
			} else {
				auctionSql = "UPDATE AUCTION SET START_TIME=:START_TIME, END_TIME=:END_TIME"
						+ " WHERE ID=:ID";
			}

			SqlParameterSource params = new MapSqlParameterSource()
					.addValue("START_TIME", new Timestamp(auction.getStartTime().getTime()))
					.addValue("END_TIME", new Timestamp(auction.getEndTime().getTime()));
					

			KeyHolder keyHolder = new GeneratedKeyHolder();
			npJdbcTemplate.update(auctionSql, params, keyHolder);
			Number id = keyHolder.getKey();
			
			auction.setId(id.intValue());

//			List<Lot> lots = auction.getLots();
//			if (lots != null) {
//				lotsSql = "INSERT INTO LOT (AUCTION_ID, STEAD_ID) VALUES (:AUCTION_ID, :STEAD_ID)";
//				for (Lot lot : lots) {
//					SqlParameterSource lotParams = new MapSqlParameterSource().addValue("AUCTION_ID", auction.getId())
//							.addValue("STEAD_ID", lot.getStead().getId());
//
//					keyHolder = new GeneratedKeyHolder();
//					npJdbcTemplate.update(lotsSql, lotParams, keyHolder);
//					id = keyHolder.getKey();
//					lot.setLotId(id.intValue());
//				}
//			}
	}

	@Override
	public Auction findById(Integer auctionId) throws Exception {
		String sql = "SELECT A.ID, A.START_TIME, A.END_TIME, S.STAT_CD, S.NAME FROM AUCTION AS A INNER JOIN STAT_CD_REF AS S"
				+ " ON A.STATUS_CODE=S.STAT_CD WHERE A.ID=:ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("ID", auctionId);
		return npJdbcTemplate.queryForObject(sql, params, new AuctionRowMapper());
	}

	@Override
	public List<Auction> findAll() throws Exception {
		String sql = "SELECT A.ID, A.START_TIME, A.END_TIME, SC.STAT_CD, SC.NAME FROM AUCTION AS A INNER JOIN STAT_CD_REF AS SC "
				+ "ON A.STATUS_CODE = SC.STAT_CD";
		return npJdbcTemplate.query(sql, new AuctionRowMapper());	
	}

	@Override
	public List<Auction> findByStatusCode(Integer statusCode) throws Exception {
		String sql = "SELECT A.ID, A.START_TIME, A.END_TIME, SC.STAT_CD, SC.NAME FROM AUCTION AS A INNER JOIN STAT_CD_REF AS SC "
				+ "ON A.STATUS_CODE = SC.STAT_CD WHERE A.STATUS_CODE=:STATUS_CODE";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STATUS_CODE", statusCode);
		return npJdbcTemplate.query(sql, params, new AuctionRowMapper());
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM AUCTION";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	public void updateStatusCode(Integer auctionId, Integer statusCode) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Changing status auction ID " + auctionId);
		}
		String sql = "UPDATE AUCTION SET STATUS_CODE=:STATUS_CODE WHERE ID=:AUCTION_ID";
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("AUCTION_ID", auctionId)
				.addValue("STATUS_CODE", statusCode);
		npJdbcTemplate.update(sql, params);
		
	}

//	@Override
//	public void cancelAuction(Auction aucton) throws Exception {
//		// TODO Auto-generated method stub
//
//	}

}
