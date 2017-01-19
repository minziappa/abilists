package com.abilists.bean.admin;

import com.abilists.bean.model.UserTaskModel;

public class TaskBean {

	private UserTaskModel userTask;
	private String userImgData;

	public UserTaskModel getUserTask() {
		return userTask;
	}
	public void setUserTask(UserTaskModel userTask) {
		this.userTask = userTask;
	}
	public String getUserImgData() {
		return userImgData;
	}
	public void setUserImgData(String userImgData) {
		this.userImgData = userImgData;
	}

}
