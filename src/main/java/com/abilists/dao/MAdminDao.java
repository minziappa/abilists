package com.abilists.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MAdminDao {

	public int insertAdmin(Map<String, Object> map) throws SQLException;
	public int insertAdminMt(Map<String, Object> map) throws SQLException;

	public int updateAdmin(Map<String, Object> map) throws SQLException;
	public int updateAdminMt(Map<String, Object> map) throws SQLException;

	public int deleteAdmin(Map<String, Object> map) throws SQLException;
	public int deleteAdminMt(Map<String, Object> map) throws SQLException;

	public int udtUsers(Map<String, Object> map) throws SQLException;

}