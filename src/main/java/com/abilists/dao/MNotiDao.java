package com.abilists.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MNotiDao {

	public int insertNotification(Map<String, Object> map) throws SQLException;
	public int updateNotification(Map<String, Object> map) throws SQLException;
	public int deleteNotification(Map<String, Object> map) throws SQLException;
	
	public int insertUserNoti(Map<String, Object> map) throws SQLException;
}