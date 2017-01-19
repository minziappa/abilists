package com.abilists.service;

import java.util.List;

import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.join.NotificationJoinUserNotiModel;
import com.abilists.bean.para.noti.DltNotiPara;
import com.abilists.bean.para.noti.IstNotiPara;
import com.abilists.bean.para.noti.SltNotiPara;
import com.abilists.bean.para.noti.UdtNotiPara;
import com.abilists.bean.para.users.SltUserNotiPara;
import com.abilists.common.bean.CommonPara;

public interface NotiService extends PagingService{

	public NotificationModel sltNotification(SltNotiPara sltNotificationPara) throws Exception;
	public List<NotificationModel> sltNotificationList(CommonPara commonPara) throws Exception;
	public List<NotificationJoinUserNotiModel> sltUserNotiList(CommonPara commonPara) throws Exception;
	public boolean insertUserNoti(SltUserNotiPara sltUserNotiPara) throws Exception;
	public boolean insertUserNotiList(CommonPara commonPara) throws Exception;

	public int sltNotificationSum() throws Exception;
	public int sltUserNotiSum(CommonPara commonPara) throws Exception;

	public boolean istNotification(IstNotiPara istNotificationPara) throws Exception;
	public boolean udtNotification(UdtNotiPara udtNotificationPara) throws Exception;
	public boolean deleteNotification(DltNotiPara deleteNotificationPara) throws Exception;

}
