package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class AdminModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int adminNo;
	private String adminId;
	private String adminName;
	private String adminEmail;
	private String adminAuth;
	private String adminStatus;
	private String userId;
	private int mrNo;
	private Date insertTime;
	private Date updateTime;

	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminAuth() {
		return adminAuth;
	}
	public void setAdminAuth(String adminAuth) {
		this.adminAuth = adminAuth;
	}
	public String getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getMrNo() {
		return mrNo;
	}
	public void setMrNo(int mrNo) {
		this.mrNo = mrNo;
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
