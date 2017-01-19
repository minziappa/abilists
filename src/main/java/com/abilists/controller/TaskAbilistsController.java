package com.abilists.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.para.task.DltTaskPara;
import com.abilists.bean.para.task.IstTaskPara;
import com.abilists.bean.para.task.SltTaskPara;
import com.abilists.bean.para.task.UdtTaskPara;
import com.abilists.bean.para.users.SltUserProjectPara;
import com.abilists.common.bean.CommonBean;
import com.abilists.service.TaskService;
import com.abilists.service.UsersService;

@Controller
@RequestMapping("/task")
public class TaskAbilistsController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(TaskAbilistsController.class);

	@Autowired
	private UsersService usersService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private CommonBean commonBean;

	@RequestMapping(value = {"istTask"})
	public String istTask(@Valid IstTaskPara istTaskPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltTaskList");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("istTask - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), istTaskPara);

		if(!taskService.validateTodayTask(istTaskPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.error.is.today.task", null, locale));
			return "errors/error";
		}

		// Execute the transaction
		if(!taskService.istTask(istTaskPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.insert.error.message", null, locale));
			return "errors/error";
		}

		return "redirect:sltTaskList";
	}

	@RequestMapping(value = {"udtTask"})
	public String udtTask(@Valid UdtTaskPara udtTaskPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltTaskList");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("udtTask - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), udtTaskPara);

		// Execute the transaction
		if(!taskService.udtTask(udtTaskPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, locale));
			return "errors/error";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:sltTaskList";
	}

    @RequestMapping(value = {"sltTaskList"})
	public String sltTaskList(SltTaskPara sltTaskPara, HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltTaskList");

		// Set user id
		this.handleSessionInfo(session, sltTaskPara);

		// Get user projects
		SltUserProjectPara sltUserProjectPara = new SltUserProjectPara();
		sltUserProjectPara.setUserId(sltTaskPara.getUserId());
		List<UserProjectsModel> userProjectsList = usersService.sltUserProjectsList(sltUserProjectPara);
		abilistsModel.setUserProjectsList(userProjectsList);

		// Set Paging list
		int intSum = taskService.sltTaskSum(sltTaskPara);
		abilistsModel.setPaging(taskService.makePaging(sltTaskPara, intSum));

		// Get user Task list
		List<UserTaskModel> userTaskList = taskService.sltTaskList(sltTaskPara);
		abilistsModel.setUserTaskList(userTaskList);
		model.addAttribute("model", abilistsModel);

		return "project/sltTaskList";
	}

	@RequestMapping(value="/sltTaskAjax")
	public @ResponseBody UserTaskModel sltTaskAjax(@RequestBody SltTaskPara sltTaskPara, 
			HttpSession session) throws Exception {

		// Set user id
		this.handleSessionInfo(session, sltTaskPara);

		// Get your task - false -> Not to convert from \r to <br/> 
		return taskService.sltTask(sltTaskPara, false);
	}

	@RequestMapping(value = {"dltTask"})
	public String dltTask(@Valid DltTaskPara dltTaskPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltTaskList");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("dltTask - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), dltTaskPara);

		// Execute the transaction
		if(!taskService.dltTask(dltTaskPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, locale));
			return "errors/error";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:sltTaskList";
	}

}