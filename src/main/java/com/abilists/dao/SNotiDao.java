package com.abilists.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.join.NotificationJoinUserNotiModel;

@Repository
public interface SNotiDao {
	public NotificationModel sltNotification(Map<String, Object> map) throws SQLException;
	public List<NotificationModel> sltNotificationList(Map<String, Object> map) throws SQLException;
	public List<NotificationModel> sltNotificationAll() throws SQLException;
	public int sltNotificationSum() throws SQLException;

	public NotificationJoinUserNotiModel sltUserNoti(Map<String, Object> map) throws SQLException;
	public List<NotificationJoinUserNotiModel> sltUserNotiList(Map<String, Object> map) throws SQLException;
	public int sltUserNotiSum(Map<String, Object> map) throws SQLException;
}