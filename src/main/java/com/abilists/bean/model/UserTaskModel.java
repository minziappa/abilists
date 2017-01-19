package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class UserTaskModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int utkNo;
	private Date utkWorkDay;
	private String utkWorkHour;
	private String utkWorkProgress;
	private String utkTask;
	private String utkStatus;
	private String utkDelete;
	private int upNo;
	private int mtNo;
	private String userId;
	private Date insertTime;
	private Date updateTime;

	public int getUtkNo() {
		return utkNo;
	}
	public void setUtkNo(int utkNo) {
		this.utkNo = utkNo;
	}
	public Date getUtkWorkDay() {
		return utkWorkDay;
	}
	public void setUtkWorkDay(Date utkWorkDay) {
		this.utkWorkDay = utkWorkDay;
	}
	public String getUtkWorkHour() {
		return utkWorkHour;
	}
	public void setUtkWorkHour(String utkWorkHour) {
		this.utkWorkHour = utkWorkHour;
	}
	public String getUtkWorkProgress() {
		return utkWorkProgress;
	}
	public void setUtkWorkProgress(String utkWorkProgress) {
		this.utkWorkProgress = utkWorkProgress;
	}
	public String getUtkTask() {
		return utkTask;
	}
	public void setUtkTask(String utkTask) {
		this.utkTask = utkTask;
	}
	public String getUtkStatus() {
		return utkStatus;
	}
	public void setUtkStatus(String utkStatus) {
		this.utkStatus = utkStatus;
	}
	public String getUtkDelete() {
		return utkDelete;
	}
	public void setUtkDelete(String utkDelete) {
		this.utkDelete = utkDelete;
	}
	public int getUpNo() {
		return upNo;
	}
	public void setUpNo(int upNo) {
		this.upNo = upNo;
	}
	public int getMtNo() {
		return mtNo;
	}
	public void setMtNo(int mtNo) {
		this.mtNo = mtNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
