package com.morozov.auction.dao;

import java.util.List;

import com.morozov.auction.model.Lot;
import com.morozov.auction.model.LotMember;

/**
 * @author Sergey Morozov
 *
 */

public interface LotMemberDao {

    public List<LotMember> findByLotId(Integer lotId) throws Exception;
		
}
