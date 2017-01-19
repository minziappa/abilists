package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AuthJoinUsersModel implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private int authorityId;
	private String authority;
	private Date insertTime;
	private Date updateTime;
	private List<UsersModel> usersList;

	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
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
	public List<UsersModel> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<UsersModel> usersList) {
		this.usersList = usersList;
	}

}