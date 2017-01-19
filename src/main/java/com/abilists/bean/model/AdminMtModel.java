package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class AdminMtModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int amNo;
	private String amTitle;
	private String amWeight;
	private String amExplain;
	private String amStatus;
	private String amDelete;
	private int mtNo;
	private Date insertTime;
	private Date updateTime;

	public int getAmNo() {
		return amNo;
	}
	public void setAmNo(int amNo) {
		this.amNo = amNo;
	}
	public String getAmTitle() {
		return amTitle;
	}
	public void setAmTitle(String amTitle) {
		this.amTitle = amTitle;
	}
	public String getAmWeight() {
		return amWeight;
	}
	public void setAmWeight(String amWeight) {
		this.amWeight = amWeight;
	}
	public String getAmExplain() {
		return amExplain;
	}
	public void setAmExplain(String amExplain) {
		this.amExplain = amExplain;
	}
	public String getAmStatus() {
		return amStatus;
	}
	public void setAmStatus(String amStatus) {
		this.amStatus = amStatus;
	}
	public String getAmDelete() {
		return amDelete;
	}
	public void setAmDelete(String amDelete) {
		this.amDelete = amDelete;
	}
	public int getMtNo() {
		return mtNo;
	}
	public void setMtNo(int mtNo) {
		this.mtNo = mtNo;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
