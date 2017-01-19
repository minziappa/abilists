package com.abilists.bean.model.join;

import java.io.Serializable;
import java.util.Date;

public class UserProjectsJoinTechModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int uptLevel;
	private String upNo;
	private String upName;
	private Date insertTime;

	public int getUptLevel() {
		return uptLevel;
	}
	public void setUptLevel(int uptLevel) {
		this.uptLevel = uptLevel;
	}
	public String getUpNo() {
		return upNo;
	}
	public void setUpNo(String upNo) {
		this.upNo = upNo;
	}
	public String getUpName() {
		return upName;
	}
	public void setUpName(String upName) {
		this.upName = upName;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

}