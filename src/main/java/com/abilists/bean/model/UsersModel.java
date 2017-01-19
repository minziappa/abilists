package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class UsersModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userNo;
	private String userId;
	private String userAuth;
	private String userName;
	private String userImgName;
	private byte[] userImg;
	private String userSex;
	private String userEmail;
	private String userAges;
	private String userProfile;
	private String userStatus;
	private String userDelete;
	private String userPwd;
	private Date insertTime;
	private Date updateTime;

	private String userImgData;

	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserAges() {
		return userAges;
	}
	public void setUserAges(String userAges) {
		this.userAges = userAges;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
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
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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
	public String getUserImgName() {
		return userImgName;
	}
	public void setUserImgName(String userImgName) {
		this.userImgName = userImgName;
	}
	public byte[] getUserImg() {
		return userImg;
	}
	public void setUserImg(byte[] userImg) {
		this.userImg = userImg;
	}
	public String getUserImgData() {
		return userImgData;
	}
	public void setUserImgData(String userImgData) {
		this.userImgData = userImgData;
	}

}
