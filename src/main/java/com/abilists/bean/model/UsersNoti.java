package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class UsersNoti implements Serializable {

	private static final long serialVersionUID = 1L;
	private int unNo;
	private String userId;
	private int notiNo;
	private String unStatus;
	private String unDelete;
	private Date insertTime;
	private Date updateTime;

	public int getUnNo() {
		return unNo;
	}
	public void setUnNo(int unNo) {
		this.unNo = unNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(int notiNo) {
		this.notiNo = notiNo;
	}
	public String getUnStatus() {
		return unStatus;
	}
	public void setUnStatus(String unStatus) {
		this.unStatus = unStatus;
	}
	public String getUnDelete() {
		return unDelete;
	}
	public void setUnDelete(String unDelete) {
		this.unDelete = unDelete;
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
