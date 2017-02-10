package com.abilists.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.configuration.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abilists.bean.TemplateModel;
import com.abilists.bean.model.UserTemp;
import com.abilists.bean.para.LoginPara;
import com.abilists.bean.para.login.CompleteConfirmPara;
import com.abilists.bean.para.login.CompleteResetPwdPara;
import com.abilists.bean.para.login.SignUpPara;
import com.abilists.bean.para.users.InputUsersInfoPara;
import com.abilists.dao.MLoginDao;
import com.abilists.dao.MUsersDao;
import com.abilists.dao.SLoginDao;
import com.abilists.service.AbstractService;
import com.abilists.service.LoginService;
import com.abilists.service.UsersService;
import com.abilists.utility.TemplateUtility;

import io.utility.email.EmailBean;
import io.utility.email.Emailer;
import io.utility.security.CipherUtility;
import io.utility.security.TokenUtility;

@Service
public class LoginServiceImpl extends AbstractService implements LoginService {

	final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private SqlSession mAbilistsDao;
	@Autowired
	private SqlSession sAbilistsDao;
	@Autowired
	private UsersService usersService;
	@Autowired
    private Configuration configuration;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private CipherUtility cipherUtility;
	@Autowired
	private String strEmailPwd;

    @Autowired
    ServletContext servletContext;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	private boolean deleteUserTemp(String token) throws Exception {

		int intResult = 0;
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userTempToken", token);

		try {
			intResult = mAbilistsDao.getMapper(MLoginDao.class).deleteUserTemp(map);
		} catch (Exception e) {
			logger.error("token1={}", token);
			logger.error("Exception error - deleteUserTemp", e);
			return false;
		}

		if(intResult < 1) {
			logger.error("deleteUserTemp error, token1={}", token);
			return false;
		}

		return true;
	}

	@Async
	@Override
	public void sendEmail(SignUpPara signUpPara) throws Exception {
		String token2 = TokenUtility.generateToken();

		EmailBean email = new EmailBean();
		email.setTo(signUpPara.getUserEmail());
		email.setFrom(configuration.getString("email.from.address"));

		TemplateModel tempModel = new TemplateModel();
		tempModel.setUserId(signUpPara.getUserEmail());
		tempModel.setUrl(configuration.getString("email.signup.url"));
		tempModel.setToken1(signUpPara.getToken());
		tempModel.setToken2(token2);

		// Get the real path on Tomcat.
		String basePath = servletContext.getRealPath("");
		logger.info("basePath > " + basePath);

		StringBuffer sbBodyPath = new StringBuffer();
		sbBodyPath.append(basePath).append(configuration.getString("email.signup.path"));

		String body = TemplateUtility.mergeTemplate(sbBodyPath.toString(), 
				configuration.getString("email.signup.filename"), tempModel);

		email.setMsg(body);
		email.setSubject("Please, complete your register");

		Emailer.sendEmail(email, configuration.getString("email.id"), strEmailPwd);
	}

	@Async
	@Override
	public void sendEmailForResetPwd(SignUpPara signUpPara) throws Exception {
		String token2 = TokenUtility.generateToken();

		EmailBean email = new EmailBean();
		email.setTo(signUpPara.getUserEmail());
		email.setFrom(configuration.getString("email.from.address"));

		TemplateModel tempModel = new TemplateModel();
		tempModel.setUserId(signUpPara.getUserEmail());
		tempModel.setUrl(configuration.getString("email.resetpwd.url"));
		tempModel.setToken1(signUpPara.getToken());
		tempModel.setToken2(token2);

		// Get the real path on Tomcat.
		String basePath = servletContext.getRealPath("");

		StringBuffer sbBodyPath = new StringBuffer();
		sbBodyPath.append(basePath).append(configuration.getString("email.resetpwd.path"));

		String body = TemplateUtility.mergeTemplate(sbBodyPath.toString(), 
				configuration.getString("email.resetpwd.filename"), tempModel);

		email.setMsg(body);
		email.setSubject("Please, complete to reset your password");

		Emailer.sendEmail(email, configuration.getString("email.id"), strEmailPwd);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public boolean insertUserTemp(SignUpPara signUpPara) throws Exception {

		String encryptedEmail = cipherUtility.encrypt(signUpPara.getUserEmail());
		long seqNum = this.getSequence("userTemp");

		Map<String, Object> mapUserTemp = new HashMap<String, Object>();

		mapUserTemp.put("userTempNum", seqNum);
		mapUserTemp.put("userTempEmail", encryptedEmail);
		mapUserTemp.put("userTempToken", signUpPara.getToken());
		mapUserTemp.put("userTempStatus", signUpPara.getUserStatus()); // 1: To reset password

		int intResult = 0;
		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			intResult = mAbilistsDao.getMapper(MLoginDao.class).insertUserTemp(mapUserTemp);
		} catch (Exception e) {
			logger.error("Exception error - updateSequece", e);
			throw e;
		}

		if(intResult < 1) {
			logger.error("insertUserTemp error, userEmail={}", signUpPara.getUserEmail());
			return false;
		}

		return true;
	}

	public UserTemp sltUserTemp(String token) throws Exception {

		UserTemp userTemp = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userTempToken", token);

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userTemp = sAbilistsDao.getMapper(SLoginDao.class).sltUserTemp(map);
		} catch (Exception e) {
			logger.error("Exception error - sltUserTemp", e);
		}

		return userTemp;
	}

	/**
	 * by email
	 * 
	 * @param userEmail
	 * @return
	 * @throws Exception
	 */
	public boolean validateEmail(String userEmail) throws Exception {

		int intCnt = 0;

		String encoded = cipherUtility.encrypt(userEmail);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userEmail", encoded);

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			intCnt = sAbilistsDao.getMapper(SLoginDao.class).sltUserCnt(map);
		} catch (Exception e) {
			logger.error("Exception error - sltUserTemp", e);
		}

		if(intCnt > 0) {
			logger.info("there is a email. cnt >> " + intCnt);
			return false;
		}

		return true;
	}

	/**
	 * by password
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean validatePwd(LoginPara loginPara) throws Exception {

		String userPwd = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", loginPara.getUserId());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			userPwd = sAbilistsDao.getMapper(SLoginDao.class).sltUserPwd(map);

			if(!passwordEncoder.matches(loginPara.getUserPwd(), userPwd)) {
				logger.error("This is wrong passwod. userId = " + loginPara.getUserId());
				return false;
			}

		} catch (Exception e) {
			logger.error("Exception error - sltUserTemp", e);
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean completeRegister(CompleteConfirmPara completeConfirmPara) throws Exception {

		// slt this by token
		UserTemp userTemp = this.sltUserTemp(completeConfirmPara.getNtoken());
		if(userTemp == null) {
			logger.error("sltting action is failed. token={}", completeConfirmPara.getNtoken());
			return false;
		}

		// Encode user password
		String encodedPassword = passwordEncoder.encode(completeConfirmPara.getPassword());

		InputUsersInfoPara inputUsersInfoPara = new InputUsersInfoPara();
		inputUsersInfoPara.setUserEmail(userTemp.getUserTempEmail());
		inputUsersInfoPara.setUserId(completeConfirmPara.getUserId());
		inputUsersInfoPara.setUserPwd(encodedPassword);

		if(!usersService.insertUsers(inputUsersInfoPara)) {
			logger.error("insertUsers action is failed. userId={}", completeConfirmPara.getUserId());
			return false;
		}

		if(!this.deleteUserTemp(completeConfirmPara.getNtoken())) {
			logger.error("Deleting action is failed. token={}", completeConfirmPara.getNtoken());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean updatePwd(CompleteResetPwdPara completeResetPwdPara) throws Exception {

		// slt this by token
		UserTemp userTemp = this.sltUserTemp(completeResetPwdPara.getNtoken());
		if(userTemp == null) {
			logger.error("sltting action is failed for updating password. ntoken={}", completeResetPwdPara.getNtoken());
			return false;
		}

		int intResult = 0;
		try {
			// Encode user password
			String encodedPassword = passwordEncoder.encode(completeResetPwdPara.getPassword());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userEmail", userTemp.getUserTempEmail());
			map.put("userPwd", encodedPassword);

			intResult = mAbilistsDao.getMapper(MUsersDao.class).updatePwd(map);
		} catch (Exception e) {
			logger.error("Exception error - updateSequece", e);
			throw e;
		}

		if(intResult < 1) {
			logger.error("updatePwd error, userEmail={}", userTemp.getUserTempEmail());
			return false;
		}

		if(!this.deleteUserTemp(completeResetPwdPara.getNtoken())) {
			logger.error("Deleting action is failed for updating password. token={}", completeResetPwdPara.getToken());
			return false;
		}

		return true;
	}

}