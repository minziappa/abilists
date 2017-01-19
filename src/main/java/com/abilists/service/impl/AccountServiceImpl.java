package com.abilists.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abilists.bean.model.UsersModel;
import com.abilists.bean.para.LoginPara;
import com.abilists.bean.para.account.UpdateEmail;
import com.abilists.bean.para.account.UdtInfoPara;
import com.abilists.bean.para.account.UdtPwd;
import com.abilists.common.bean.CommonPara;
import com.abilists.dao.MUsersDao;
import com.abilists.dao.SUsersDao;
import com.abilists.service.AbstractService;
import com.abilists.service.AccountService;
import com.abilists.service.LoginService;

import io.utility.security.CipherUtility;

@Service
public class AccountServiceImpl extends AbstractService implements AccountService {

	final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private SqlSession sAbilistsDao;
	@Autowired
	private LoginService loginService;
	@Autowired
	private CipherUtility cipherUtility;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UsersModel sltInfo(CommonPara commonPara) throws Exception {
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

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean updateInfo(UdtInfoPara updateInfoPara) throws Exception {
	
		int intResult = 0;
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", updateInfoPara.getUserId());
		map.put("userName", updateInfoPara.getUserName());
		map.put("userSex", updateInfoPara.getUserSex());
		map.put("userAges", updateInfoPara.getUserAges());
		map.put("userProfile", updateInfoPara.getUserProfile());
		map.put("userStatus", "0");
	
		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).updateUser(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
	
		if(intResult < 1) {
			logger.error("updateUser error, userId={}", updateInfoPara.getUserId());
			return false;
		}
	
		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean updatedPwd(UdtPwd updatePassword) throws Exception {
		int intResult = 0;

		try {

			LoginPara loginPara = new LoginPara();
			loginPara.setUserId(updatePassword.getUserId());
			loginPara.setUserPwd(updatePassword.getCurrentPwd());

			if(!loginService.validatePwd(loginPara)) {
				logger.error("This is worng password in updatePwd. userId=" + updatePassword.getUserId());
				return false;
			}

			// Encode user password
			String encodedPassword = passwordEncoder.encode(updatePassword.getPassword());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", updatePassword.getUserId());
			map.put("userPwd", encodedPassword);
			map.put("userStatus", "0");

			intResult = mAbilistsDao.getMapper(MUsersDao.class).updatePwd(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		if(intResult < 1) {
			logger.error("updateUser error, userId={}", updatePassword.getUserId());
			// throw new Exception("Exception updated password thrown to abort exception");
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean updatedEmail(UpdateEmail updateEmail) throws Exception {
		int intResult = 0;

		try {
			// Encode user new email
			String encryptedEmail = cipherUtility.encrypt(updateEmail.getNewUserEmail());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", updateEmail.getUserId());
			map.put("userEmail", encryptedEmail);

			intResult = mAbilistsDao.getMapper(MUsersDao.class).updateEmail(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		if(intResult < 1) {
			logger.error("updateUser error, userId={}", updateEmail.getUserId());
			// throw new Exception("Exception updated password thrown to abort exception");
			return false;
		}

		return true;
	}

}