package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class UserTemp implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userTempNum;
	private String userTempEmail;
	private String userTempToken;
	private String userTempStatus;
	private Date insertDate;
	private Date updateDate;

	public long getUserTempNum() {
		return userTempNum;
	}
	public void setUserTempNum(long userTempNum) {
		this.userTempNum = userTempNum;
	}
	public String getUserTempEmail() {
		return userTempEmail;
	}
	public void setUserTempEmail(String userTempEmail) {
		this.userTempEmail = userTempEmail;
	}
	public String getUserTempToken() {
		return userTempToken;
	}
	public void setUserTempToken(String userTempToken) {
		this.userTempToken = userTempToken;
	}
	public String getUserTempStatus() {
		return userTempStatus;
	}
	public void setUserTempStatus(String userTempStatus) {
		this.userTempStatus = userTempStatus;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
