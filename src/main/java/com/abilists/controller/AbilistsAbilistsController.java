package com.abilists.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.model.UsersModel;
import com.abilists.bean.para.noti.SltNotiPara;
import com.abilists.bean.para.task.SltTaskPara;
import com.abilists.bean.para.users.SltUserProjectPara;
import com.abilists.common.bean.CommonPara;
import com.abilists.service.IndexService;
import com.abilists.service.NotiService;
import com.abilists.service.TaskService;
import com.abilists.service.UsersService;

@Controller
@RequestMapping("/abilists")
public class AbilistsAbilistsController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(AbilistsAbilistsController.class);

	@Autowired
	private UsersService usersService;
	@Autowired
	private IndexService indexService;
	@Autowired
	private NotiService notiService;
	@Autowired
	private TaskService taskService;
	@Autowired
    private Configuration configuration;

	/*
	 * Index page on Ablists
	 */
    @RequestMapping(value = {"/", "", "index"}, method=RequestMethod.GET)
	public String index(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("abilists");
		abilistsModel.setMenu("index");

		CommonPara commonPara = new CommonPara();

		// Set user id.
		this.handleSessionInfo(session, commonPara);
		// Get data to make a chart for project.
		abilistsModel.setUserProjectsSumByYearList(usersService.sltUserProjectsSumByYearList(commonPara));
		// Get user list for index page
		List<UsersModel> userList = indexService.sltUsersList();
		// Set your picture
		for (UsersModel user : userList) {
			String imgData = this.handleReadImage(user.getUserId(), configuration.getString("upload.path.img"));
			user.setUserImgData(imgData);
		}
		abilistsModel.setUsersList(userList);;

		// Set user id
		SltUserProjectPara sltUserProjectPara = new SltUserProjectPara();
		sltUserProjectPara.setUserId(commonPara.getUserId());

		// Get user projects
		List<UserProjectsModel> userProjectsList = usersService.sltUserProjectsList(sltUserProjectPara);
		abilistsModel.setUserProjectsList(userProjectsList);

		// Get user today task
		abilistsModel.setUserTask(taskService.sltTodayTask(commonPara));

		// Get user today task
		abilistsModel.setUserTaskList(taskService.sltTaskList(commonPara));

	   	model.addAttribute("model", abilistsModel);

		return "abilists/index";
	}

	@RequestMapping(value = {"/{userId}"})
	public String userId(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("abilists");

	   	model.addAttribute("model", abilistsModel);

		return "abilists/userProfile";
	}

    @RequestMapping(value = {"/charts"}, method=RequestMethod.GET)
	public String charts(@RequestParam("option") String option, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("abilists");

	   	model.addAttribute("model", abilistsModel);

	   	if(option.equals("1")) {
	   		return "abilists/charts1";	
	   	}
		return "abilists/charts2";
	}

	@RequestMapping(value = {"viewTask/{utkWorkDay}"})
	public String viewTask(@PathVariable String utkWorkDay, 
			HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("users");
		abilistsModel.setMenu("viewUserProjects");

		// Get a project
		SltTaskPara sltTaskPara = new SltTaskPara();
		sltTaskPara.setUtkWorkDay(utkWorkDay);

		// Set user id
		this.handleSessionInfo(session, sltTaskPara);

		UserTaskModel userTask = taskService.sltTask(sltTaskPara, true);
		abilistsModel.setUserTask(userTask);
		model.addAttribute("model", abilistsModel);

		return "abilists/viewTask";
	}

	@RequestMapping(value = {"viewProjects/{upNo}"})
	public String viewProjects(@PathVariable String upNo, 
			HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("users");
		abilistsModel.setMenu("viewUserProjects");

		// Get a project
		SltUserProjectPara sltUserProjectPara = new SltUserProjectPara();
		sltUserProjectPara.setUpNo(upNo);
		// Set user id
		this.handleSessionInfo(session, sltUserProjectPara);
		UserProjectsModel userProjects = usersService.sltUserProjects(sltUserProjectPara);
		abilistsModel.setUserProjects(userProjects);
		model.addAttribute("model", abilistsModel);

		return "abilists/viewProjects";
	}

	@RequestMapping(value = {"viewNoti/{notiNo}"})
	public String viewNoti(@PathVariable String notiNo, 
			HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("users");
		abilistsModel.setMenu("viewNoti");

		// Get a project
		SltNotiPara sltNotificationPara = new SltNotiPara();
		sltNotificationPara.setNotiNo(notiNo);
		// Set user id
		this.handleSessionInfo(session, sltNotificationPara);
		NotificationModel notification = notiService.sltNotification(sltNotificationPara);
		abilistsModel.setNotification(notification);
		model.addAttribute("model", abilistsModel);

		return "abilists/viewNoti";
	}

}