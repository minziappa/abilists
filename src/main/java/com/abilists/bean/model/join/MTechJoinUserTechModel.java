package com.abilists.bean.model.join;

import java.io.Serializable;

public class MTechJoinUserTechModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int mtNo;
	private String mtSkill;
	private String mtName;
	private String utKind;
	private String utLevel;

	public int getMtNo() {
		return mtNo;
	}

	public void setMtNo(int mtNo) {
		this.mtNo = mtNo;
	}

	public String getMtSkill() {
		return mtSkill;
	}

	public void setMtSkill(String mtSkill) {
		this.mtSkill = mtSkill;
	}

	public String getMtName() {
		return mtName;
	}

	public void setMtName(String mtName) {
		this.mtName = mtName;
	}

	public String getUtKind() {
		return utKind;
	}

	public void setUtKind(String utKind) {
		this.utKind = utKind;
	}

	public String getUtLevel() {
		return utLevel;
	}

	public void setUtLevel(String utLevel) {
		this.utLevel = utLevel;
	}


}