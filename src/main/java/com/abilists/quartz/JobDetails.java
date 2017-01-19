package com.abilists.quartz;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.UsersModel;
import com.abilists.service.IndexService;
import com.abilists.service.TaskService;

public class JobDetails {

	final Logger logger = LoggerFactory.getLogger(JobDetails.class);

	@Autowired
	private IndexService indexService;
	@Autowired
    private Configuration configuration;

	public JobDetails() {
	}

	public void executeJob() {

		try {

	        AbilistsModel abilists = new AbilistsModel();

//			// slt user project
//			List<UserProjectsModel> userProjectsList = new ArrayList<>();
//			userProjectsList = indexService.sltUserProjectsList();
//			logger.info("userProjectList's count > " + userProjectsList.size());
//	        abilists.setUserProjectsList(userProjectsList);
//
//			logger.info("-- Start projects --");
//			String fromPath = configuration.getString("template.projects.from.path");
//			String fromFile = configuration.getString("template.projects.from.filename");
//			String toPath = configuration.getString("template.projects.to.path");
//			String toFile = configuration.getString("template.projects.to.filename");
//			indexService.makeIndexPage(fromPath, fromFile, toPath, toFile, abilists);
//			logger.info("--End projects--");

//			// slt user task
//			List<UserTaskModel> userTaskList = new ArrayList<>();
//			userTaskList = indexService.sltTaskList();
//			logger.info("task's count > " + userTaskList.size());
//	        abilists.setUserTaskList(userTaskList);
//
//			logger.info("-- Start task --");
//			String fromPath = configuration.getString("template.task.from.path");
//			String fromFile = configuration.getString("template.task.from.filename");
//			String toPath = configuration.getString("template.task.to.path");
//			String toFile = configuration.getString("template.task.to.filename");
//			indexService.makeIndexPage(fromPath, fromFile, toPath, toFile, abilists);
//			logger.info("--End task--");

			// slt notification
			List<NotificationModel> notificationList = new ArrayList<>();
			notificationList = indexService.sltNotificationList();
			logger.info("notificationList's count > " + notificationList.size());
	        abilists.setNotificationList(notificationList);

			logger.info("-- Start noti --");
			String fromPath = configuration.getString("template.noti.from.path");
			String fromFile = configuration.getString("template.noti.from.filename");
			String toPath = configuration.getString("template.noti.to.path");
			String toFile = configuration.getString("template.noti.to.filename");
			indexService.makeIndexPage(fromPath, fromFile, toPath, toFile, abilists);
			logger.info("--End noti--");

		} catch (Exception e) {
			logger.error("Exception >> ", e);
		}

	}
}
