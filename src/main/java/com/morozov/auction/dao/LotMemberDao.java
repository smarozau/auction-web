package com.morozov.auction.dao;

import java.util.List;

import com.morozov.auction.model.Lot;
import com.morozov.auction.model.LotMember;
import com.morozov.auction.model.User;

/**
 * @author Sergey Morozov
 *
 */

public interface LotMemberDao {

	public void saveLotMember(LotMember member) throws Exception;
	
    public List<User> findByLotId(Integer lotId) throws Exception;
    
    public List<Lot> findByLotMemberId(Integer lotMemberId) throws Exception;
		
}
