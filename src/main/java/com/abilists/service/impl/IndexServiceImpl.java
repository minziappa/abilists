package com.abilists.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
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
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.UsersModel;
import com.abilists.bean.para.IndexPara;
import com.abilists.dao.MUsersDao;
import com.abilists.dao.SNotiDao;
import com.abilists.dao.SUsersDao;
import com.abilists.service.AbstractService;
import com.abilists.service.IndexService;
import com.abilists.service.UsersService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.utility.HtmlEscape;
import freemarker.template.utility.XmlEscape;

@Service
public class IndexServiceImpl extends AbstractService implements IndexService {

	final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

	@Autowired
	private SqlSession mAbilistsDao;
	@Autowired
	private SqlSession sAbilistsDao;
    @Autowired
    ServletContext servletContext;
    @Autowired
    UsersService usersService;

	@Autowired
    private org.apache.commons.configuration.Configuration configuration;

	public boolean makeIndexPage(String fromPath, String fromFile, 
			String toPath, String toFile, AbilistsModel abilists) throws Exception {

		// Get the real path on Tomcat.
		String basePath = servletContext.getRealPath("");
		logger.info("basePath > " + basePath);

		StringBuilder sbTemplatePath = new StringBuilder();
		sbTemplatePath.append(basePath);
		sbTemplatePath.append(fromPath);

		logger.info("templatePath >> " + sbTemplatePath.toString());

		StringBuilder sbOutPutPath = new StringBuilder();
		sbOutPutPath.append(basePath);
		sbOutPutPath.append(toPath);

		logger.info("sbOutPutPath >> " + sbOutPutPath.toString());

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(sbTemplatePath.toString()));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        //---------------------------------------------------------------
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setConfiguration(cfg);
        Map<String, Object> map = new HashMap<>();
        map.put("xml_escape", new XmlEscape());
        map.put("html_escape", new HtmlEscape());
        configurer.setFreemarkerVariables(map);

        Template temp = configurer.getConfiguration().getTemplate(fromFile);
        //---------------------------------------------------------------

        /* Create a data-model */
        Map<String, AbilistsModel> root = new HashMap<>();
        root.put("model", abilists);
//      //---------------------------------------------------------------
//        /* Get the template (uses cache internally) */
//        Template temp = cfg.getTemplate(configuration.getString("template.from.filename"));
//      //---------------------------------------------------------------

        /* Merge data-model with template */
        // Writer out = new OutputStreamWriter(System.out); // Out put to console.

        String toFilename = sbOutPutPath.append(toFile).toString();
        try (
        		OutputStream os = new FileOutputStream(toFilename);
                Writer out = new OutputStreamWriter(os);
        	) 
        {
            temp.process(root, out);
        }

		return true;
	}




//	public boolean makeIndexPage(String fromPath, String fromFile, String toPath, String toFile) throws Exception {
//
//		// slt user profile
//		List<UsersModel> usersList = new ArrayList<>();
//		usersList = indexService.sltUsersList();
//		logger.info("usersList's count > " + usersList.size());
//
//		// slt user project
//		List<UserProjectsModel> userProjectsList = new ArrayList<>();
//		userProjectsList = indexService.sltUserProjectsList();
//		logger.info("userProjectList's count > " + userProjectsList.size());
//
//		// slt notification
//		List<NotificationModel> notificationList = new ArrayList<>();
//		notificationList = indexService.sltNotificationList();
//		logger.info("notificationList's count > " + notificationList.size());
//
//		// Get the real path on Tomcat.
//		String basePath = servletContext.getRealPath("");
//		logger.info("basePath > " + basePath);
//
//		StringBuilder sbTemplatePath = new StringBuilder();
//		sbTemplatePath.append(basePath);
//		sbTemplatePath.append(configuration.getString("template.from.path"));
//
//		logger.info("templatePath >> " + sbTemplatePath.toString());
//
//		StringBuilder sbOutPutPath = new StringBuilder();
//		sbOutPutPath.append(basePath);
//		sbOutPutPath.append(configuration.getString("template.to.path"));
//
//		logger.info("sbOutPutPath >> " + sbOutPutPath.toString());
//
//        /* Create and adjust the configuration singleton */
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
//        cfg.setDirectoryForTemplateLoading(new File(sbTemplatePath.toString()));
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        cfg.setLogTemplateExceptions(false);
//
//        //---------------------------------------------------------------
//        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//        configurer.setConfiguration(cfg);
//        Map<String, Object> map = new HashMap<>();
//        map.put("xml_escape", new XmlEscape());
//        map.put("html_escape", new HtmlEscape());
//        configurer.setFreemarkerVariables(map);
//
//        Template temp = configurer.getConfiguration().getTemplate(configuration.getString("template.from.filename"));
//        //---------------------------------------------------------------
//
//        AbilistsModel abilists = new AbilistsModel();
//        abilists.setUsersList(usersList);
//        abilists.setUserProjectsList(userProjectsList);
//        abilists.setNotificationList(notificationList);
//
//        /* Create a data-model */
//        Map<String, AbilistsModel> root = new HashMap<>();
//        root.put("model", abilists);
////      //---------------------------------------------------------------
////        /* Get the template (uses cache internally) */
////        Template temp = cfg.getTemplate(configuration.getString("template.from.filename"));
////      //---------------------------------------------------------------
//
//        /* Merge data-model with template */
//        // Writer out = new OutputStreamWriter(System.out); // Out put to console.
//
//        String toFilename = sbOutPutPath.append(configuration.getString("template.to.filename")).toString();
//        try (
//        		OutputStream os = new FileOutputStream(toFilename);
//                Writer out = new OutputStreamWriter(os);
//        	) 
//        {
//            temp.process(root, out);
//        }
//
//		return true;
//	}

// Backup
//	public boolean makeIndexPage() throws Exception {
//
//		// slt user profile
//		List<UsersModel> usersList = new ArrayList<>();
//		usersList = indexService.sltUsersList();
//		logger.info("usersList's count > " + usersList.size());
//
//		// slt user project
//		List<UserProjectsModel> userProjectsList = new ArrayList<>();
//		userProjectsList = indexService.sltUserProjectsList();
//		logger.info("userProjectList's count > " + userProjectsList.size());
//
//		// slt notification
//		List<NotificationModel> notificationList = new ArrayList<>();
//		notificationList = indexService.sltNotificationList();
//		logger.info("notificationList's count > " + notificationList.size());
//
//		// Get the real path on Tomcat.
//		String basePath = servletContext.getRealPath("");
//		logger.info("basePath > " + basePath);
//
//		StringBuilder sbTemplatePath = new StringBuilder();
//		sbTemplatePath.append(basePath);
//		sbTemplatePath.append(configuration.getString("template.from.path"));
//
//		logger.info("templatePath >> " + sbTemplatePath.toString());
//
//		StringBuilder sbOutPutPath = new StringBuilder();
//		sbOutPutPath.append(basePath);
//		sbOutPutPath.append(configuration.getString("template.to.path"));
//
//		logger.info("sbOutPutPath >> " + sbOutPutPath.toString());
//
//        /* Create and adjust the configuration singleton */
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
//        cfg.setDirectoryForTemplateLoading(new File(sbTemplatePath.toString()));
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        cfg.setLogTemplateExceptions(false);
//
//        AbilistsModel abilists = new AbilistsModel();
//        abilists.setUsersList(usersList);
//        abilists.setUserProjectsList(userProjectsList);
//        abilists.setNotificationList(notificationList);
//
//        /* Create a data-model */
//        Map<String, AbilistsModel> root = new HashMap<>();
//        root.put("model", abilists);
//
//        /* Get the template (uses cache internally) */
//        Template temp = cfg.getTemplate(configuration.getString("template.from.filename"));
//
//        /* Merge data-model with template */
//        // Writer out = new OutputStreamWriter(System.out); // Out put to console.
//
//        String toFilename = sbOutPutPath.append(configuration.getString("template.to.filename")).toString();
//        try (
//        		OutputStream os = new FileOutputStream(toFilename);
//                Writer out = new OutputStreamWriter(os);
//        	) 
//        {
//            temp.process(root, out);
//        }
//
//		return true;
//	}

	/*
	 * TODO
	 * Duplicated class with the AdminUsersServiceImpl.sltUsersList()
	 * @see com.abilists.service.IndexService#sltUsersList()
	 */
	@Override
	public List<UsersModel> sltUsersList() throws Exception {
		List<UsersModel> usersList = new ArrayList<UsersModel>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowPage", 0);
		map.put("row", configuration.getInt("paging.row.cnt"));

		try {
			usersList = sAbilistsDao.getMapper(SUsersDao.class).sltUsersList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return usersList;
	}

	@Override
	public List<UserProjectsModel> sltUserProjectsList() throws Exception {
		List<UserProjectsModel> userProjectsList = null;
	
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nowPage", 0);
			map.put("row", configuration.getInt("paging.row.cnt"));
	
			userProjectsList = sAbilistsDao.getMapper(SUsersDao.class).sltUserProjectsList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
	
		return userProjectsList;
	}

	@Override
	public List<NotificationModel> sltNotificationList() throws Exception {
		List<NotificationModel> notificationList = null;
	
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nowPage", 0);
			map.put("row", configuration.getInt("paging.row.cnt"));
	
			notificationList = sAbilistsDao.getMapper(SNotiDao.class).sltNotificationList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
	
		return notificationList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean insertUser(IndexPara indexPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", indexPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).insertUser(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("insertSample error, userName={}", indexPara.getUserId());
			return false;
		}
		
		return true;
	}

//	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
//	@Override
//	public boolean updateUser(IndexPara indexPara) throws Exception {
//
//		int intResult = 0;
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userId", indexPara.getUserId());
//
//		try {
//			intResult = mAbilistsDao.getMapper(MUsersDao.class).updateUser(map);
//		} catch (Exception e) {
//			logger.error("Exception error", e);
//		}
//		if(intResult < 1) {
//			logger.error("insertSample error, userName={}", indexPara.getUserId());
//			return false;
//		}
//
//		return true;
//	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteUser(IndexPara indexPara) throws Exception{
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", indexPara.getUserId());

		try {
			intResult = mAbilistsDao.getMapper(MUsersDao.class).deleteUser(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("insertSample error, userName={}", indexPara.getUserId());
			return false;
		}

		return true;
	}

	@Override
	public boolean updateUser(IndexPara indexPara) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}