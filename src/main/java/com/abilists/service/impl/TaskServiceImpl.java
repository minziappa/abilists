package com.abilists.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.para.task.DltTaskPara;
import com.abilists.bean.para.task.IstTaskPara;
import com.abilists.bean.para.task.SltTaskPara;
import com.abilists.bean.para.task.UdtTaskPara;
import com.abilists.common.bean.CommonPara;
import com.abilists.dao.MUsersDao;
import com.abilists.dao.SUsersDao;
import com.abilists.service.AbstractService;
import com.abilists.service.TaskService;

import io.utility.letter.DateUtility;
import io.utility.security.CipherUtility;

@Service
public class TaskServiceImpl extends AbstractService implements TaskService {

	final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	public static final String REQ_PARA = "update";

	final int ROW_COUNT_TASK = 3;

	@Autowired
	private SqlSession mAbilistsDao;
	@Autowired
	private SqlSession sAbilistsDao;
	@Autowired
	private SqlSession mAbilistsBatchDao;
	@Autowired
	private CipherUtility cipherUtility;
	@Autowired
    private Configuration configuration;

	@Override
	public boolean istTask(IstTaskPara istTaskPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("utkWorkDay", istTaskPara.getUtkWorkDay());
		map.put("utkTask", istTaskPara.getUtkTask());
		map.put("userId", istTaskPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).istUserTask(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("insertUser error, upName={}", istTaskPara.getUtkWorkDay());
			return false;
		}

		return true;
	}

	@Override
	public boolean udtTask(UdtTaskPara udtTaskPara) throws Exception {
		int intResult = 0;

		SltTaskPara sltTaskPara = new SltTaskPara();
		sltTaskPara.setUtkWorkDay(udtTaskPara.getUtkWorkDay());
		sltTaskPara.setUserId(udtTaskPara.getUserId());

		UserTaskModel userTask = this.sltTask(sltTaskPara, false);

		// If there is a data in today, It can not input a new data into your task list.
		if(userTask == null) {
			logger.warn("There is no data. today=" + udtTaskPara.getUtkWorkDay());

			IstTaskPara istTaskPara = new IstTaskPara();
			istTaskPara.setUserId(udtTaskPara.getUserId());
			istTaskPara.setUtkWorkDay(udtTaskPara.getUtkWorkDay());
			istTaskPara.setUtkTask(udtTaskPara.getUtkTask());

			return this.istTask(istTaskPara);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("utkNo", udtTaskPara.getUtkNo());
		map.put("utkWorkDay", udtTaskPara.getUtkWorkDay());
		map.put("utkTask", udtTaskPara.getUtkTask());
		map.put("userId", udtTaskPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).udtUserTask(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("udtTask error, utkWorkDay={}", udtTaskPara.getUtkWorkDay());
			return false;
		}

		return true;
	}

	/*
	 * Get user task list for showing in home navigation
	 * 
	 * @see com.abilists.service.TaskService#sltTaskList(com.abilists.common.bean.CommonPara)
	 */
	@Override
	public List<UserTaskModel> sltTaskList(CommonPara commonPara) throws Exception {
		List<UserTaskModel> userTaskList = null;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", commonPara.getUserId());
			map.put("nowPage", 0);
			map.put("row", ROW_COUNT_TASK);

			userTaskList = sAbilistsDao.getMapper(SUsersDao.class).sltUserTaskList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return userTaskList;
	}

	@Override
	public List<UserTaskModel> sltTaskList(SltTaskPara sltTaskPara) throws Exception {

		List<UserTaskModel> userTaskList = new ArrayList<>();

		// Get now page
		int nowPage = sltTaskPara.getNowPage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", sltTaskPara.getUserId());
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.cnt"));
		map.put("row", configuration.getInt("paging.row.cnt"));

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userTaskList = sAbilistsDao.getMapper(SUsersDao.class).sltUserTaskList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		if(userTaskList == null) {
			logger.warn("There is no user task list. userId=" + sltTaskPara.getUserId());
			return null;
		}

		// Replace the carriage return for HTML Tag
		String strTask = "";
		for (UserTaskModel task : userTaskList) {

			if(task.getUtkTask() != null && task.getUtkTask().length() > 50) {
				strTask = task.getUtkTask().substring(0, 50);
			} else {
				strTask = task.getUtkTask();
			}

			// String rePack = strTask.replaceAll("[\r]", "<br/>");
			task.setUtkTask(strTask);
		}

		return userTaskList;
	}

	@Override
	public UserTaskModel sltTask(SltTaskPara sltTaskPara, boolean forView) throws Exception {

		UserTaskModel userTask = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("utkNo", sltTaskPara.getUtkNo());
		map.put("utkWorkDay", sltTaskPara.getUtkWorkDay());
		map.put("userId", sltTaskPara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userTask = sAbilistsDao.getMapper(SUsersDao.class).sltUserTask(map);

			if(userTask == null) {
				logger.warn("There is no data. utkWorkDay=" + sltTaskPara.getUtkWorkDay());
				return null;
			}

			String rePack = userTask.getUtkTask();
			if(forView) {
				// Replace the carriage return for HTML Tag
				rePack = userTask.getUtkTask().replaceAll("[\r]", "<br/>");
			}

			userTask.setUtkTask(rePack);

		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return userTask;
	}

	@Override
	public int sltTaskSum(SltTaskPara sltTaskPara) throws Exception {
		int sum = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", sltTaskPara.getUserId());

		try {
			sum = sAbilistsDao.getMapper(SUsersDao.class).sltUserTaskSum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

	@Override
	public boolean dltTask(DltTaskPara dltTaskPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("utkNo", dltTaskPara.getUtkNo());
		map.put("userId", dltTaskPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).dltUserTask(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("dltTask error, utkNo={}", dltTaskPara.getUtkNo());
			return false;
		}

		return true;
	}

	@Override
	public UserTaskModel sltTodayTask(CommonPara commonPara) throws Exception {

		UserTaskModel userTask = null;

		String strToday = DateUtility.formatDate(DateUtility.getNowTime(), "yyyy-MM-dd");

		logger.info("today >> " + strToday);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("utkWorkDay", strToday);
		map.put("userId", commonPara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userTask = sAbilistsDao.getMapper(SUsersDao.class).sltUserTask(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return userTask;
	}

	@Override
	public boolean validateTodayTask(IstTaskPara istTaskPara) throws Exception {

		SltTaskPara sltTaskPara = new SltTaskPara();
		sltTaskPara.setUtkWorkDay(istTaskPara.getUtkWorkDay());
		sltTaskPara.setUtkTask(istTaskPara.getUtkTask());

		UserTaskModel userTask = this.sltTask(sltTaskPara, false);

		if(userTask != null) {
			logger.warn("There is a today task. ");
			return false;
		}
		return true;
	}

}