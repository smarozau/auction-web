package com.morozov.auction.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.morozov.auction.dao.LotMemberDao;
import com.morozov.auction.model.Lot;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.model.User;
import com.morozov.auction.service.LotMemberService;

public class LotMemberServiceImpl implements LotMemberService {

private Logger logger = LoggerFactory.getLogger(LotMemberServiceImpl.class);
	
	private LotMemberDao lotMemberDao;
	
	public void setLotMemberDao(LotMemberDao lotMemberDao) {
		this.lotMemberDao = lotMemberDao;
	}
	
	@Override
	@Transactional
	public void saveLotMember(LotMember member) throws Exception {
		
		lotMemberDao.saveLotMember(member);
	}

	@Override
	public List<User> findByLotId(Integer lotId) throws Exception {
		
		return lotMemberDao.findByLotId(lotId);
	}

	@Override
	public List<Lot> findByLotMemberId(Integer lotMemberId) throws Exception {
		
		return lotMemberDao.findByLotMemberId(lotMemberId);
	}

}
