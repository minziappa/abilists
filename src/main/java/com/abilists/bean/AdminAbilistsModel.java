package com.abilists.bean;

import java.util.List;

import com.abilists.bean.admin.TaskBean;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.sum.AdminProjectsSumByStatusModel;
import com.abilists.bean.model.sum.AdminUsersSumByMonthModel;
import com.abilists.common.bean.CommonModel;

public class AdminAbilistsModel extends CommonModel {

	private List<AdminUsersSumByMonthModel> adminUsersSumByMonthList;
	private List<AdminProjectsSumByStatusModel> adminProjectsSumByStatusList;

	private List<TaskBean> taskBeanList;
	private List<UserTaskModel> userTaskList;

	// Sum Techs
	private int techsSum;
	// Sum Industry
	private int industrySum;
	// Sum Roles
	private int rolesSum;
	// Notification
	private int notiSum;

	public List<AdminUsersSumByMonthModel> getAdminUsersSumByMonthList() {
		return adminUsersSumByMonthList;
	}

	public void setAdminUsersSumByMonthList(List<AdminUsersSumByMonthModel> adminUsersSumByMonthList) {
		this.adminUsersSumByMonthList = adminUsersSumByMonthList;
	}

	public List<AdminProjectsSumByStatusModel> getAdminProjectsSumByStatusList() {
		return adminProjectsSumByStatusList;
	}

	public void setAdminProjectsSumByStatusList(List<AdminProjectsSumByStatusModel> adminProjectsSumByStatusList) {
		this.adminProjectsSumByStatusList = adminProjectsSumByStatusList;
	}

	public List<TaskBean> getTaskBeanList() {
		return taskBeanList;
	}

	public void setTaskBeanList(List<TaskBean> taskBeanList) {
		this.taskBeanList = taskBeanList;
	}

	public List<UserTaskModel> getUserTaskList() {
		return userTaskList;
	}

	public void setUserTaskList(List<UserTaskModel> userTaskList) {
		this.userTaskList = userTaskList;
	}

	public int getTechsSum() {
		return techsSum;
	}

	public void setTechsSum(int techsSum) {
		this.techsSum = techsSum;
	}

	public int getIndustrySum() {
		return industrySum;
	}

	public void setIndustrySum(int industrySum) {
		this.industrySum = industrySum;
	}

	public int getRolesSum() {
		return rolesSum;
	}

	public void setRolesSum(int rolesSum) {
		this.rolesSum = rolesSum;
	}

	public int getNotiSum() {
		return notiSum;
	}

	public void setNotiSum(int notiSum) {
		this.notiSum = notiSum;
	}

}
