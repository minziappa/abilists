package com.abilists.bean;

import java.util.List;

import com.abilists.bean.model.MIndustryModel;
import com.abilists.bean.model.MRoleModel;
import com.abilists.bean.model.MTechDetailModel;
import com.abilists.bean.model.MTechModel;
import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.UserProjectTechModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.UserTechModel;
import com.abilists.bean.model.UserTemp;
import com.abilists.bean.model.UsersModel;
import com.abilists.bean.model.join.MIndustryJoinUserProjectsModel;
import com.abilists.bean.model.join.MTechJoinUserTechModel;
import com.abilists.bean.model.join.UserProjectsJoinTechModel;
import com.abilists.bean.model.sum.UserProjectsSumByYearModel;
import com.abilists.bean.para.admin.SltMIndustryPara;
import com.abilists.bean.para.admin.SltMRolePara;
import com.abilists.bean.para.admin.SltMTechDetailPara;
import com.abilists.bean.para.admin.SltMTechPara;
import com.abilists.common.bean.CommonModel;

public class AbilistsModel extends CommonModel {

	private UserTemp userTemp;
	private UsersModel users;
	private List<UsersModel> usersList;
	private UserTechModel userTech;
	private List<UserTechModel> userTechList;
	private UserProjectsModel userProjects;
	private List<UserProjectsModel> userProjectsList;
	private List<UserTaskModel> userTaskList;
	private UserTaskModel userTask;
	private List<UserProjectsSumByYearModel> userProjectsSumByYearList;

	private UserProjectTechModel userProjectTech;
	private List<UserProjectTechModel> userProjectTechList;
	private MTechModel mTech;
	private List<MTechModel> mTechList;
	private SltMTechPara sltMTechPara;

	private List<MTechDetailModel> mTechDetailList;
	private SltMTechDetailPara sltMTechDetailPara;

	private List<MRoleModel> mRoleList;
	private SltMRolePara sltMRolePara;

	private List<MIndustryModel> mIndustryList;
	private SltMIndustryPara sltMIndustryPara;

	private NotificationModel notification;
	private List<NotificationModel> notificationList;

	private List<MTechJoinUserTechModel> mTechJoinUserTechList;
	private List<MIndustryJoinUserProjectsModel> mIndustryJoinUserProjectsList;
	private List<UserProjectsJoinTechModel> userProjectsJoinTechList;

	public UserTemp getUserTemp() {
		return userTemp;
	}

	public void setUserTemp(UserTemp userTemp) {
		this.userTemp = userTemp;
	}

	public UsersModel getUsers() {
		return users;
	}

	public void setUsers(UsersModel users) {
		this.users = users;
	}

	public List<UsersModel> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UsersModel> usersList) {
		this.usersList = usersList;
	}

	public UserTechModel getUserTech() {
		return userTech;
	}

	public void setUserTech(UserTechModel userTech) {
		this.userTech = userTech;
	}

	public List<UserTechModel> getUserTechList() {
		return userTechList;
	}

	public void setUserTechList(List<UserTechModel> userTechList) {
		this.userTechList = userTechList;
	}

	public UserProjectsModel getUserProjects() {
		return userProjects;
	}

	public void setUserProjects(UserProjectsModel userProjects) {
		this.userProjects = userProjects;
	}

	public List<UserProjectsModel> getUserProjectsList() {
		return userProjectsList;
	}

	public List<UserTaskModel> getUserTaskList() {
		return userTaskList;
	}

	public void setUserTaskList(List<UserTaskModel> userTaskList) {
		this.userTaskList = userTaskList;
	}

	public UserTaskModel getUserTask() {
		return userTask;
	}

	public void setUserTask(UserTaskModel userTask) {
		this.userTask = userTask;
	}

	public void setUserProjectsList(List<UserProjectsModel> userProjectsList) {
		this.userProjectsList = userProjectsList;
	}

	public List<UserProjectsSumByYearModel> getUserProjectsSumByYearList() {
		return userProjectsSumByYearList;
	}

	public void setUserProjectsSumByYearList(List<UserProjectsSumByYearModel> userProjectsSumByYearList) {
		this.userProjectsSumByYearList = userProjectsSumByYearList;
	}

	public UserProjectTechModel getUserProjectTech() {
		return userProjectTech;
	}

	public void setUserProjectTech(UserProjectTechModel userProjectTech) {
		this.userProjectTech = userProjectTech;
	}

	public List<UserProjectTechModel> getUserProjectTechList() {
		return userProjectTechList;
	}

	public void setUserProjectTechList(List<UserProjectTechModel> userProjectTechList) {
		this.userProjectTechList = userProjectTechList;
	}

	public MTechModel getmTech() {
		return mTech;
	}

	public void setmTech(MTechModel mTech) {
		this.mTech = mTech;
	}

	public List<MTechModel> getmTechList() {
		return mTechList;
	}

	public void setmTechList(List<MTechModel> mTechList) {
		this.mTechList = mTechList;
	}

	public SltMTechPara getSltMTechPara() {
		return sltMTechPara;
	}

	public void setSltMTechPara(SltMTechPara sltMTechPara) {
		this.sltMTechPara = sltMTechPara;
	}

	public List<MTechDetailModel> getmTechDetailList() {
		return mTechDetailList;
	}

	public void setmTechDetailList(List<MTechDetailModel> mTechDetailList) {
		this.mTechDetailList = mTechDetailList;
	}

	public SltMTechDetailPara getSltMTechDetailPara() {
		return sltMTechDetailPara;
	}

	public void setSltMTechDetailPara(SltMTechDetailPara sltMTechDetailPara) {
		this.sltMTechDetailPara = sltMTechDetailPara;
	}

	public List<MRoleModel> getmRoleList() {
		return mRoleList;
	}

	public void setmRoleList(List<MRoleModel> mRoleList) {
		this.mRoleList = mRoleList;
	}

	public SltMRolePara getSltMRolePara() {
		return sltMRolePara;
	}

	public void setSltMRolePara(SltMRolePara sltMRolePara) {
		this.sltMRolePara = sltMRolePara;
	}

	public List<MIndustryModel> getmIndustryList() {
		return mIndustryList;
	}

	public void setmIndustryList(List<MIndustryModel> mIndustryList) {
		this.mIndustryList = mIndustryList;
	}

	public SltMIndustryPara getSltMIndustryPara() {
		return sltMIndustryPara;
	}

	public void setSltMIndustryPara(SltMIndustryPara sltMIndustryPara) {
		this.sltMIndustryPara = sltMIndustryPara;
	}

	public NotificationModel getNotification() {
		return notification;
	}

	public void setNotification(NotificationModel notification) {
		this.notification = notification;
	}

	public List<NotificationModel> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<NotificationModel> notificationList) {
		this.notificationList = notificationList;
	}

	public List<MTechJoinUserTechModel> getmTechJoinUserTechList() {
		return mTechJoinUserTechList;
	}

	public void setmTechJoinUserTechList(List<MTechJoinUserTechModel> mTechJoinUserTechList) {
		this.mTechJoinUserTechList = mTechJoinUserTechList;
	}

	public List<MIndustryJoinUserProjectsModel> getmIndustryJoinUserProjectsList() {
		return mIndustryJoinUserProjectsList;
	}

	public void setmIndustryJoinUserProjectsList(List<MIndustryJoinUserProjectsModel> mIndustryJoinUserProjectsList) {
		this.mIndustryJoinUserProjectsList = mIndustryJoinUserProjectsList;
	}

	public List<UserProjectsJoinTechModel> getUserProjectsJoinTechList() {
		return userProjectsJoinTechList;
	}

	public void setUserProjectsJoinTechList(List<UserProjectsJoinTechModel> userProjectsJoinTechList) {
		this.userProjectsJoinTechList = userProjectsJoinTechList;
	}

}
