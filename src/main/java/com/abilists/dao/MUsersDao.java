package com.abilists.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MUsersDao {

	public int insertUser(Map<String, Object> map) throws SQLException;
	public int insertUserTech(Map<String, Object> map) throws SQLException;
	public int insertUserProjects(Map<String, Object> map) throws SQLException;
	public int insertUserProjectTech(Map<String, Object> map) throws SQLException;
	public int istUserTask(Map<String, Object> map) throws SQLException;

	public int updateUserTech(Map<String, Object> map) throws SQLException;
	public int updateUserProjects(Map<String, Object> map) throws SQLException;
	public int updateUserProjectTech(Map<String, Object> map) throws SQLException;
	
	public int updateUser(Map<String, Object> map) throws SQLException;
	public int updatePwd(Map<String, Object> map) throws SQLException;
	public int updateEmail(Map<String, Object> map) throws SQLException;
	public int udtUserTask(Map<String, Object> map) throws SQLException;

	public int deleteUser(Map<String, Object> map) throws SQLException;
	public int deleteUserTech(Map<String, Object> map) throws SQLException;
	public int deleteUserProjects(Map<String, Object> map) throws SQLException;
	public int deleteUserProjectTech(Map<String, Object> map) throws SQLException;
	public int deleteUserProjectTechByUp(Map<String, Object> map) throws SQLException;

	public int dltUserTask(Map<String, Object> map) throws SQLException;

	public long updateSequeceUserTemp(Map<String, Object> map) throws SQLException;
	public long updateSequeceUser(Map<String, Object> map) throws SQLException;

	public int insertUserTemp(Map<String, Object> map) throws SQLException;
}