package com.abilists.bean.para.admin;

import com.abilists.common.bean.CommonPara;

public class UdtUsersPara extends CommonPara {

	private String userAuth;
	private String userStatus;
	private String userDelete;

	public String getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserDelete() {
		return userDelete;
	}
	public void setUserDelete(String userDelete) {
		this.userDelete = userDelete;
	}

}
