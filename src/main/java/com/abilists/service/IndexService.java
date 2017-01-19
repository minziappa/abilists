package com.abilists.service;

import java.util.List;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.UsersModel;
import com.abilists.bean.para.IndexPara;

public interface IndexService {

	public boolean makeIndexPage(String fromPath, String fromFile, 
			String toPath, String toFile, AbilistsModel abilists) throws Exception;

//	public UsersModel sltUser() throws Exception;
	public List<UsersModel> sltUsersList() throws Exception;
	public boolean insertUser(IndexPara indexPara) throws Exception;
	public boolean updateUser(IndexPara indexPara) throws Exception;
	public boolean deleteUser(IndexPara indexPara) throws Exception;

	public List<UserProjectsModel> sltUserProjectsList() throws Exception;
	public List<NotificationModel> sltNotificationList() throws Exception;

}
