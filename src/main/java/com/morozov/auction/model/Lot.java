package com.morozov.auction.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lot database table.
 * 
 */
@Entity
@NamedQuery(name="Lot.findAll", query="SELECT l FROM Lot l")
public class Lot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOT_ID")
	private int lotId;

	//bi-directional many-to-one association to Stead
	@ManyToOne
	private Stead stead;

	private List<LotMember> lotMembers;

	public Lot() {
	}

	public int getLotId() {
		return this.lotId;
	}

	public void setLotId(int lotId) {
		this.lotId = lotId;
	}

	public Stead getStead() {
		return this.stead;
	}

	public void setStead(Stead stead) {
		this.stead = stead;
	}

	public List<LotMember> getLotMembers() {
		return this.lotMembers;
	}

	public void setLotMembers(List<LotMember> lotMembers) {
		this.lotMembers = lotMembers;
	}

}