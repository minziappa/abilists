package com.abilists.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.UserProjectTechModel;
import com.abilists.bean.model.UserTechModel;
import com.abilists.bean.model.UsersModel;
import com.abilists.bean.model.sum.UserProjectsSumByYearModel;
import com.abilists.bean.para.users.UdtProjectsPara;
import com.abilists.bean.para.users.UdtProjectTechPara;
import com.abilists.bean.para.users.UdtUserTechPara;
import com.abilists.common.Common;
import com.abilists.common.bean.CommonPara;
import com.abilists.bean.para.task.SltTaskPara;
import com.abilists.bean.para.users.DltProjectTechPara;
import com.abilists.bean.para.users.DltProjectsPara;
import com.abilists.bean.para.users.InputUsersInfoPara;
import com.abilists.bean.para.users.IstProjectsPara;
import com.abilists.bean.para.users.SltUserProjectPara;
import com.abilists.bean.para.users.SltProjectTechPara;
import com.abilists.bean.para.users.IstProjectTechPara;
import com.abilists.bean.para.users.SltUserTechPara;
import com.abilists.bean.para.users.UpdateSettingsPara;
import com.abilists.dao.MUsersDao;
import com.abilists.dao.SSumDao;
import com.abilists.dao.SUsersDao;
import com.abilists.service.AbstractService;
import com.abilists.service.UsersService;

import io.utility.security.CipherUtility;

@Service
public class UsersServiceImpl extends AbstractService implements UsersService {

	final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	public static final String REQ_PARA = "update";

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

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private boolean insertUserTech(UdtUserTechPara updateUserTechPara) throws Exception {
		Map<String, Object> map = null;

		try {
			
			logger.info(" MtNo count >>> " + updateUserTechPara.getMtNo().length);
			logger.info(" UtDetail count >>> " + updateUserTechPara.getUtDetail().length);

			int intTotal = updateUserTechPara.getMtNo().length;
			
			for(int i=0; i < intTotal; i++) {

				map = new HashMap<String, Object>();
				map.put("mtNo", updateUserTechPara.getMtNo()[i]);
				map.put("utSkill", updateUserTechPara.getUtSkill());
				map.put("utKind", updateUserTechPara.getUtKind()[i]);
				map.put("utLevel", updateUserTechPara.getUtLevel()[i]);
				if(intTotal == updateUserTechPara.getUtDetail().length) {
					map.put("utDetail", updateUserTechPara.getUtDetail()[i]);
				}
				map.put("userId", updateUserTechPara.getUserId());

				mAbilistsBatchDao.getMapper(MUsersDao.class).insertUserTech(map);
			}
		} catch (Exception e) {
			logger.error("Exception error", e);
			return false;
		}

		return true;
	}

//	@Override
//	public UsersModel sltUser(CommonPara commonPara) throws Exception {
//		UsersModel user = null;
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userId", commonPara.getUserId());
//
//		try {
//			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
//			user = sAbilistsDao.getMapper(SUsersDao.class).sltUser(map);
//			if(user == null) {
//				return null;
//			}
//			String decryptedEmail = cipherUtility.decrypt(user.getUserEmail()); 
//			user.setUserEmail(decryptedEmail);
//		} catch (Exception e) {
//			logger.error("Exception error", e);
//		}
//
//		return user;
//	}

	public UserProjectsModel sltUserProjects(SltUserProjectPara sltUserProjectPara) throws Exception {

		UserProjectsModel userProjects = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upNo", sltUserProjectPara.getUpNo());
		map.put("userId", sltUserProjectPara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userProjects = sAbilistsDao.getMapper(SUsersDao.class).sltUserProjects(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return userProjects;
	}

	@Override
	public List<UserProjectsModel> sltUserProjectsList(SltUserProjectPara sltUserProjectPara) throws Exception {

		List<UserProjectsModel> userProjectsList = new ArrayList<>();

		// Get now page
		int nowPage = sltUserProjectPara.getNowPage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", sltUserProjectPara.getUserId());
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.cnt"));
		map.put("row", configuration.getInt("paging.row.cnt"));

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userProjectsList = sAbilistsDao.getMapper(SUsersDao.class).sltUserProjectsList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
	
		return userProjectsList;
	}

	@Override
	public UserProjectTechModel sltUserProjectTech(SltProjectTechPara sltUserProjectTechPara) throws Exception {
		UserProjectTechModel userProjectTech = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uptNo", sltUserProjectTechPara.getUptNo());
		map.put("userId", sltUserProjectTechPara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userProjectTech = sAbilistsDao.getMapper(SUsersDao.class).sltUserProjectTech(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return userProjectTech;
	}

	@Override
	public List<UserProjectTechModel> sltUserProjectTechList(SltProjectTechPara sltUserProjectTechPara) throws Exception {
		List<UserProjectTechModel> userProjectTechList = null;

		// Get now page
		int nowPage = sltUserProjectTechPara.getNowPage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upNo", sltUserProjectTechPara.getUpNo());
		map.put("userId", sltUserProjectTechPara.getUserId());
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.cnt"));
		map.put("row", configuration.getInt("paging.row.cnt"));

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userProjectTechList = sAbilistsDao.getMapper(SUsersDao.class).sltUserProjectTechList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return userProjectTechList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean insertUsers(InputUsersInfoPara inputUsersInfoPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", inputUsersInfoPara.getUserId());
		map.put("userPwd", inputUsersInfoPara.getUserPwd());
		map.put("userCode", inputUsersInfoPara.getUserCode());
		map.put("userName", inputUsersInfoPara.getUserName());
		map.put("userSex", inputUsersInfoPara.getUserSex());
		map.put("userEmail", inputUsersInfoPara.getUserEmail());
		map.put("userProfile", inputUsersInfoPara.getUserProfile());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).insertUser(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("insertUser error, userId={}", inputUsersInfoPara.getUserId());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean insertUserProjects(IstProjectsPara insertUserProjectsPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upName", insertUserProjectsPara.getUpName());
		map.put("upRole", insertUserProjectsPara.getUpRole());
		map.put("upIndustrial", insertUserProjectsPara.getUpIndustrial());
		map.put("upMembers", insertUserProjectsPara.getUpMembers());
		map.put("upRole", insertUserProjectsPara.getUpRole());
		map.put("upExplain", insertUserProjectsPara.getUpExplain());
		map.put("userId", insertUserProjectsPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).insertUserProjects(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("insertUser error, upName={}", insertUserProjectsPara.getUpName());
			throw new Exception("Exception inserted thrown to abort exception");
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean insertUserProjectTech(IstProjectTechPara insertUserProjectTechPara) throws Exception {

		Map<String, Object> map = null;
		try {

			String [] mtNo = insertUserProjectTechPara.getMtNo();
			String [] uptKind = insertUserProjectTechPara.getUptKind();
			String [] uptLevel = insertUserProjectTechPara.getUptLevel();
			String [] uptDetail = insertUserProjectTechPara.getUptDetail();

			for(int i=0; i < mtNo.length; i++) {
				logger.info("mtno[" + i + "]=" + insertUserProjectTechPara.getMtNo()[i]);
				map = new HashMap<String, Object>();

				map.put("mtNo", mtNo[i]);
				map.put("upNo", insertUserProjectTechPara.getUpNo());
				map.put("uptKind", (uptKind == null || uptKind.length < 1) ? null : uptKind[i]);
				map.put("uptLevel", (uptLevel == null || uptLevel.length < 1) ? null : uptLevel[i]);
				map.put("uptDetail", (uptDetail == null || uptDetail.length < 1) ? null : uptDetail[i]);
				map.put("userId", insertUserProjectTechPara.getUserId());

				mAbilistsBatchDao.getMapper(MUsersDao.class).insertUserProjectTech(map);
			}
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteUsers(InputUsersInfoPara inputUsersInfoPara) throws Exception{
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", inputUsersInfoPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).deleteUser(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("deleteUser error, userName={}", inputUsersInfoPara.getUserId());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateUserProjects(UdtProjectsPara inputUserProjectsPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upNo", inputUserProjectsPara.getUpNo());
		map.put("upName", inputUserProjectsPara.getUpName());
		map.put("upRole", inputUserProjectsPara.getUpRole());
		map.put("upIndustrial", inputUserProjectsPara.getUpIndustrial());
		map.put("upMembers", inputUserProjectsPara.getUpMembers());
		map.put("upRole", inputUserProjectsPara.getUpRole());
		map.put("upExplain", inputUserProjectsPara.getUpExplain());
		map.put("userId", inputUserProjectsPara.getUserId());
		map.put("userStatus", "0");

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).updateUserProjects(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		if(intResult < 1) {
			logger.error("updateUser error, upName={}", inputUserProjectsPara.getUpName());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean dltUserProjectsWithTech(DltProjectsPara dltProjectsPara) throws Exception {
		int intResult = 0;

		// Delete a project's Techs
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upNo", dltProjectsPara.getUpNo());
		map.put("userId", dltProjectsPara.getUserId());
		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).deleteUserProjectTechByUp(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.warn("dltUserProjectsWithTech error, upNo={}, userId={}", dltProjectsPara.getUpNo(), 
					dltProjectsPara.getUserId());
		}

		// Delete a project
		map = new HashMap<String, Object>();
		map.put("upNo", dltProjectsPara.getUpNo());
		map.put("userId", dltProjectsPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).deleteUserProjects(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("deleteUser error, upNo={}, userId={}", dltProjectsPara.getUpNo(), 
					dltProjectsPara.getUserId());
			return false;
		}

		return true;
	}

//	/**
//	 * 
//	 * Register a user data
//	 */
//	@Override
//	public boolean registerUser(InputUsersInfoPara inputUsersInfoPara) throws Exception {
//	
//		boolean blnResult = false;
//		UsersModel user = this.sltUsers(inputUsersInfoPara.getUserId());
//	
//		if(user == null) {
//			blnResult = this.insertUsers(inputUsersInfoPara);
//		} else {
//			blnResult = this.updateUsers(inputUsersInfoPara);
//		}
//	
//		if(!blnResult) {
//			logger.error("registerUser error, userId={}", inputUsersInfoPara.getUserId());
//			return false;
//		}
//	
//		return true;
//	}

	@Override
	public List<UserProjectsModel> searchUserProjectsList(UdtProjectsPara inputUserProjectsPara)
			throws Exception {

		List<UserProjectsModel> userProjectsList = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", inputUserProjectsPara.getUserId());
		map.put("upName", inputUserProjectsPara.getUpName());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userProjectsList = sAbilistsDao.getMapper(SUsersDao.class).searchUserProjectsList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return userProjectsList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateUserProjectTech(UdtProjectTechPara updateUserProjectTechPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uptNo", updateUserProjectTechPara.getUptNo());
		map.put("uptKind", updateUserProjectTechPara.getUptKind());
		map.put("uptLevel", updateUserProjectTechPara.getUptLevel());
		map.put("uptDetail", updateUserProjectTechPara.getUptDetail());
		map.put("uptStatus", updateUserProjectTechPara.getUptStatus());
		map.put("mtNo", updateUserProjectTechPara.getMtNo());
		map.put("userId", updateUserProjectTechPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).updateUserProjectTech(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("updateUserProjectTech error, userId={}", updateUserProjectTechPara.getUserId());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteUserProjectTech(DltProjectTechPara dltProjectTechPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uptNo", dltProjectTechPara.getUptNo());
		map.put("userId", dltProjectTechPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).deleteUserProjectTech(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("deleteUserProjectTech error, uptNo={}, userId={}", dltProjectTechPara.getUptNo(), 
					dltProjectTechPara.getUserId());
			return false;
		}

		return true;
	}

	@Override
	public List<UserTechModel> sltUserTechList(SltUserTechPara sltUserTechPara) throws Exception {
		List<UserTechModel> userTechList = new ArrayList<>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", sltUserTechPara.getUserId());
		map.put("utSkill", sltUserTechPara.getUtSkill());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userTechList = sAbilistsDao.getMapper(SUsersDao.class).sltUserTechList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		if(userTechList.size() < 1) {
			logger.warn("The count slted is 0, userId={}, utSkill={}", 
					sltUserTechPara.getUserId(), sltUserTechPara.getUtSkill());			
		}

		logger.info("sltUserTechList count >> " + userTechList.size());

		return userTechList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateUserTech(UdtUserTechPara updateUserTechPara) throws Exception {

		// Binary Search 
		if(Arrays.binarySearch(updateUserTechPara.getReq(), REQ_PARA) >= 0) {
			if(!this.deleteUserTech(updateUserTechPara)) {
				logger.error("deleteUserTech error, mtNo={}, userId={}", updateUserTechPara.getMtNo(), 
						updateUserTechPara.getUserId());
				throw new Exception();
			}
		}

		if(!this.insertUserTech(updateUserTechPara)) {
			logger.error("insertUserTech error, mtNo={}, userId={}", updateUserTechPara.getMtNo(), 
					updateUserTechPara.getUserId());
			throw new Exception();
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteUserTech(UdtUserTechPara updateUserTechPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("utSkill", updateUserTechPara.getUtSkill());
		map.put("userId", updateUserTechPara.getUserId());

		try {
			intResult = mAbilistsBatchDao.getMapper(MUsersDao.class).deleteUserTech(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("deleteUserTech error, mtNo={}, userName={}", updateUserTechPara.getMtNo(), 
					updateUserTechPara.getUserId());
		}

		return true;
	}

	@Override
	public boolean registerUser(UpdateSettingsPara updateSettingsPara) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int sltUserProjectsSum(SltUserProjectPara sltUserProjectPara) throws Exception {
		int sum = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", sltUserProjectPara.getUserId());

		try {
			sum = sAbilistsDao.getMapper(SUsersDao.class).sltUserProjectsSum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

	@Override
	public int sltUserProjectTechSum(SltProjectTechPara sltUserProjectTechPara) throws Exception {
		int sum = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", sltUserProjectTechPara.getUserId());

		try {
			sum = sAbilistsDao.getMapper(SUsersDao.class).sltUserProjectTechSum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

	@Override
	public List<UserProjectsSumByYearModel> sltUserProjectsSumByYearList(CommonPara commonPara) throws Exception {
		List<UserProjectsSumByYearModel> userProjectsSumByYearList = new ArrayList<>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", commonPara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userProjectsSumByYearList = sAbilistsDao.getMapper(SSumDao.class).sltUserProjectsSumByYear(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return userProjectsSumByYearList;
	}

}