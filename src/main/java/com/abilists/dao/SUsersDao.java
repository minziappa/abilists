package com.abilists.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.UserProjectTechModel;
import com.abilists.bean.model.UserTechModel;
import com.abilists.bean.model.UsersModel;

@Repository
public interface SUsersDao {

	public UsersModel sltUser(Map<String, Object> map) throws SQLException;
	public List<UsersModel> sltUsersList(Map<String, Object> map) throws SQLException;
	public List<UsersModel> sltUsersList() throws SQLException;

	public int sltUsersSum() throws SQLException;
	public int sltUserProjectsSum(Map<String, Object> map) throws SQLException;
	public int sltUserProjectTechSum(Map<String, Object> map) throws SQLException;
	public int sltUserTaskSum(Map<String, Object> map) throws SQLException;

	public List<UsersModel> srhUsersList(Map<String, Object> map) throws SQLException;

	public UserTechModel sltUserTech(Map<String, Object> map) throws SQLException;
	public List<UserTechModel> sltUserTechList(Map<String, Object> map) throws SQLException;
	public UserProjectsModel sltUserProjects(Map<String, Object> map) throws SQLException;
	
	public List<UserProjectsModel> searchUserProjectsList(Map<String, Object> map) throws SQLException;
	public List<UserProjectsModel> sltUserProjectsList(Map<String, Object> map) throws SQLException;
	
	public UserTaskModel sltUserTask(Map<String, Object> map) throws SQLException;
	public List<UserTaskModel> sltUserTaskList(Map<String, Object> map) throws SQLException;

	public UserProjectTechModel sltUserProjectTech(Map<String, Object> map) throws SQLException;
	public List<UserProjectTechModel> sltUserProjectTechList(Map<String, Object> map) throws SQLException;

}