package com.abilists.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abilists.bean.model.UsersModel;
import com.abilists.bean.model.join.MIndustryJoinUserProjectsModel;
import com.abilists.bean.model.join.MTechJoinUserTechModel;
import com.abilists.bean.model.join.UserProjectsJoinTechModel;
import com.abilists.common.bean.CommonPara;
import com.abilists.dao.SJoinDao;
import com.abilists.dao.SUsersDao;
import com.abilists.service.AbstractService;
import com.abilists.service.ProfileService;
import com.abilists.service.UsersService;

import io.utility.security.CipherUtility;

@Service
public class ProfileServiceImpl extends AbstractService implements ProfileService {

	final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

	@Autowired
	private SqlSession sAbilistsDao;
	@Autowired
	private CipherUtility cipherUtility;
	@Autowired
	private UsersService usersService;

	@Override
	public UsersModel sltUser(CommonPara commonPara) throws Exception {
		UsersModel user = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			user = sAbilistsDao.getMapper(SUsersDao.class).sltUser(map);
			if(user == null) {
				return null;
			}
			String decryptedEmail = cipherUtility.decrypt(user.getUserEmail()); 
			user.setUserEmail(decryptedEmail);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return user;
	}

	@Override
	public List<MTechJoinUserTechModel> sltUserTechList(CommonPara commonPara) throws Exception {
		List<MTechJoinUserTechModel> mTechJoinUserTechList = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			mTechJoinUserTechList = sAbilistsDao.getMapper(SJoinDao.class).sltMTechJoinUserTechList(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return mTechJoinUserTechList;
	}

	@Override
	public List<MIndustryJoinUserProjectsModel> sltUserProjectsList(CommonPara commonPara) throws Exception {
		List<MIndustryJoinUserProjectsModel> mIndustryJoinUserProjectsList = null;

		logger.info("lang --> " + commonPara.getLang());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());
		map.put("mlCode", commonPara.getLang());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			mIndustryJoinUserProjectsList = sAbilistsDao.getMapper(SJoinDao.class).sltMIndustryJoinUserProjectsList(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return mIndustryJoinUserProjectsList;
	}

	@Override
	public List<UserProjectsJoinTechModel> sltUserProjectsAndTechList(CommonPara commonPara) throws Exception {
		List<UserProjectsJoinTechModel> userProjectsJoinTechModelList = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());
		map.put("mlCode", commonPara.getLang());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userProjectsJoinTechModelList = sAbilistsDao.getMapper(SJoinDao.class).sltUserProjectsJoinTechList(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return userProjectsJoinTechModelList;
	}

}
