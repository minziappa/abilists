package com.abilists.bean.para.users;

import javax.validation.constraints.NotNull;

import com.abilists.annotation.Array;
import com.abilists.common.bean.CommonPara;

/**
 * 
 * @author njoonk
 */
public class InsertUserTechPara extends CommonPara {

	@NotNull(message = "skill")
	private String skill;

	@NotNull(message = "mtNo")
	@Array(message = "mtNo must be array")
    private String[] mtNo;

	private String[] utKind;

    private String[] utLevel;

    private String[] utDetail;

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String[] getMtNo() {
		return mtNo;
	}

	public void setMtNo(String[] mtNo) {
		this.mtNo = mtNo;
	}

//	public String[] getUtSkill() {
//		return utSkill;
//	}
//
//	public void setUtSkill(String[] utSkill) {
//		this.utSkill = utSkill;
//	}

	public String[] getUtKind() {
		return utKind;
	}

	public void setUtKind(String[] utKind) {
		this.utKind = utKind;
	}

	public String[] getUtLevel() {
		return utLevel;
	}

	public void setUtLevel(String[] utLevel) {
		this.utLevel = utLevel;
	}

	public String[] getUtDetail() {
		return utDetail;
	}

	public void setUtDetail(String[] utDetail) {
		this.utDetail = utDetail;
	}

}