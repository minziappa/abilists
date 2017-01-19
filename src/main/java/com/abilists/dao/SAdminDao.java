package com.abilists.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abilists.bean.model.AdminMtModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.sum.AdminProjectsSumByStatusModel;
import com.abilists.bean.model.sum.AdminUsersSumByMonthModel;

@Repository
public interface SAdminDao {

	public AdminMtModel sltAdmin(Map<String, Object> map) throws SQLException;
	public AdminMtModel sltAdminMt(Map<String, Object> map) throws SQLException;

	public List<AdminMtModel> sltAdminList(Map<String, Object> map) throws SQLException;
	public List<AdminMtModel> sltAdminMtList(Map<String, Object> map) throws SQLException;

	public List<AdminUsersSumByMonthModel> sltAdminUserSumByMonth(Map<String, Object> map) throws SQLException;
	public List<AdminProjectsSumByStatusModel> sltAdminProjectsSumByStatus() throws SQLException;

	public List<UserTaskModel> sltAdminTaskList(Map<String, Object> map) throws SQLException;
	public int sltAdminTaskSum() throws SQLException;

}