package com.abilists.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MLoginDao {

	public int insertUserTemp(Map<String, Object> map) throws SQLException;
	public int deleteUserTemp(Map<String, Object> map) throws SQLException;

}