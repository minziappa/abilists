package com.abilists.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.join.NotificationJoinUserNotiModel;
import com.abilists.bean.para.noti.DltNotiPara;
import com.abilists.bean.para.noti.IstNotiPara;
import com.abilists.bean.para.noti.SltNotiPara;
import com.abilists.bean.para.noti.UdtNotiPara;
import com.abilists.bean.para.users.SltUserNotiPara;
import com.abilists.common.bean.CommonPara;
import com.abilists.dao.MNotiDao;
import com.abilists.dao.SNotiDao;
import com.abilists.service.AbstractService;
import com.abilists.service.IndexService;
import com.abilists.service.NotiService;

import io.utility.letter.DateUtility;

@Service
public class NotiServiceImpl extends AbstractService implements NotiService{

	final Logger logger = LoggerFactory.getLogger(NotiServiceImpl.class);

	@Autowired
	private SqlSession mAbilistsDao;
	@Autowired
	private SqlSession sAbilistsDao;
	@Autowired
	private SqlSession mAbilistsBatchDao;

    @Autowired
    ServletContext servletContext;

	@Autowired
    private org.apache.commons.configuration.Configuration configuration;

	@Override
	public NotificationModel sltNotification(SltNotiPara sltNotificationPara) throws Exception {

		NotificationModel notification = new NotificationModel();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notiNo", sltNotificationPara.getNotiNo());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			notification = sAbilistsDao.getMapper(SNotiDao.class).sltNotification(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return notification;
	}

	@Override
	public List<NotificationModel> sltNotificationList(CommonPara commonPara) throws Exception {
		List<NotificationModel> usersList = new ArrayList<NotificationModel>();

		// Get now page
		int nowPage = commonPara.getNowPage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.cnt"));
		map.put("row", configuration.getInt("paging.row.cnt"));

		try {
			usersList = sAbilistsDao.getMapper(SNotiDao.class).sltNotificationList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return usersList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean istNotification(IstNotiPara istNotificationPara) throws Exception {
		int intResult = 0;

		// TODO : check this range time later
//		// start date
//		String [] strStartDate = istNotificationPara.getNotiStart().split("-");		
//		Date startDate = DateUtility.getStartToday(Integer.parseInt(strStartDate[0]), 
//				Integer.parseInt(strStartDate[1]), Integer.parseInt(strStartDate[2]), 0);
//		// end date
//		String [] strEndDate = istNotificationPara.getNotiEnd().split("-");
//		Date endDate = DateUtility.getEndToday(Integer.parseInt(strEndDate[0]), 
//				Integer.parseInt(strEndDate[1]), Integer.parseInt(strEndDate[2]), 0);

		// Get a sequence number
		long seqNum = this.getSequence("notification");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notiNo", seqNum);
		map.put("notiTitle", istNotificationPara.getNotiTitle());
		map.put("notiContents", istNotificationPara.getNotiContents());
		map.put("notiKind", istNotificationPara.getNotiKind());
//		map.put("notiStart", startDate);
//		map.put("notiEnd", endDate);

		try {
			intResult = mAbilistsDao.getMapper(MNotiDao.class).insertNotification(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("istNotification error, NotiTitle={}", istNotificationPara.getNotiTitle());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean udtNotification(UdtNotiPara udtNotificationPara) throws Exception {

		int intResult = 0;

//		TODO
//		if(udtNotificationPara.getNotiStart() != null) {
//			// start date
//			String [] strStartDate = udtNotificationPara.getNotiStart().split("-");
//			Date startDate = DateUtility.getStartToday(Integer.parseInt(strStartDate[0]), 
//					Integer.parseInt(strStartDate[1]), Integer.parseInt(strStartDate[2]), 0);
//			// end date
//			String [] strEndDate = udtNotificationPara.getNotiEnd().split("-");
//			Date endDate = DateUtility.getEndToday(Integer.parseInt(strEndDate[0]),
//					Integer.parseInt(strEndDate[1]), Integer.parseInt(strEndDate[2]), 0);
//		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notiNo", udtNotificationPara.getNotiNo());
		map.put("notiTitle", udtNotificationPara.getNotiTitle());
		map.put("notiContents", udtNotificationPara.getNotiContents());
		map.put("notiKind", udtNotificationPara.getNotiKind());
//		map.put("notiStart", startDate);
//		map.put("notiEnd", endDate);

		try {
			intResult = mAbilistsDao.getMapper(MNotiDao.class).updateNotification(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("udtNotification error, NotiNo={}", udtNotificationPara.getNotiNo());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteNotification(DltNotiPara deleteNotificationPara) throws Exception{
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notiNo", deleteNotificationPara.getNotiNo());

		try {
			intResult = mAbilistsDao.getMapper(MNotiDao.class).deleteNotification(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
		if(intResult < 1) {
			logger.error("deleteNotification error, NotiNo={}", deleteNotificationPara.getNotiNo());
			return false;
		}

		return true;
	}

	@Override
	public int sltNotificationSum() throws Exception {
		int sum = 0;

		try {
			sum = sAbilistsDao.getMapper(SNotiDao.class).sltNotificationSum();
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean insertUserNotiList(CommonPara commonPara) throws Exception {

		List<NotificationJoinUserNotiModel> notificationJoinUserNotiList = new ArrayList<NotificationJoinUserNotiModel>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());

		try {
			notificationJoinUserNotiList = sAbilistsDao.getMapper(SNotiDao.class).sltUserNotiList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		try {
			for(NotificationJoinUserNotiModel notificationJoinUserNoti: notificationJoinUserNotiList) {
				map = new HashMap<String, Object>();
				map.put("notiNo", notificationJoinUserNoti.getNotiNo());
				map.put("userId", commonPara.getUserId());
				mAbilistsBatchDao.getMapper(MNotiDao.class).insertUserNoti(map);
			}
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean insertUserNoti(SltUserNotiPara sltUserNotiPara) throws Exception {
		NotificationJoinUserNotiModel notificationJoinUserNoti = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notiNo", sltUserNotiPara.getNotiNo());
		map.put("userId", sltUserNotiPara.getUserId());

		try {
			notificationJoinUserNoti = sAbilistsDao.getMapper(SNotiDao.class).sltUserNoti(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		if(notificationJoinUserNoti == null) {
			return false;
		}

		try {
			map = new HashMap<String, Object>();
			map.put("notiNo", notificationJoinUserNoti.getNotiNo());
			map.put("userId", sltUserNotiPara.getUserId());
			mAbilistsDao.getMapper(MNotiDao.class).insertUserNoti(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return true;
	}

	@Override
	public List<NotificationJoinUserNotiModel> sltUserNotiList(CommonPara commonPara) throws Exception {

		List<NotificationJoinUserNotiModel> notificationJoinUserNotiList = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());

		try {
			notificationJoinUserNotiList = sAbilistsDao.getMapper(SNotiDao.class).sltUserNotiList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return notificationJoinUserNotiList;
	}

	@Override
	public int sltUserNotiSum(CommonPara commonPara) throws Exception {
		int sum = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());

		try {
			sum = sAbilistsDao.getMapper(SNotiDao.class).sltUserNotiSum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}


}