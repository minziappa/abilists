package com.abilists.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.model.NotificationModel;
import com.abilists.bean.model.join.NotificationJoinUserNotiModel;
import com.abilists.bean.para.noti.SltNotiPara;
import com.abilists.bean.para.users.SltUserNotiPara;
import com.abilists.common.bean.CommonPara;
import com.abilists.service.NotiService;

@Controller
@RequestMapping("/noti")
public class NotiAbilistsController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(NotiAbilistsController.class);

	@Autowired
	private NotiService notiService;

	/**
	 * For your notification
	 * 
	 * @param commonPara
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"sltNotiForUserList"})
	public String sltNotiForUserList(@Valid CommonPara commonPara, HttpSession session, 
			ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("users");
		abilistsModel.setMenu("sltNotiForUserList");

		// Set user id
		this.handleSessionInfo(session, commonPara);
		// Insert user notification
		notiService.insertUserNotiList(commonPara);
		// Set the count of the notification again
		session.setAttribute("notiCnt", notiService.sltUserNotiSum(commonPara));

		// Set Paging list
		int intSum = notiService.sltNotificationSum();
		abilistsModel.setPaging(notiService.makePaging(commonPara, intSum));

		// Execute the transaction
		abilistsModel.setNotificationList(notiService.sltNotificationList(commonPara));

		model.addAttribute("model", abilistsModel);

		return "noti/sltNotiForUserList";
	}

	/**
	 * For your notification
	 * 
	 * @param commonPara
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"sltNotiForUser/{notiNo}"})
	public String sltNotiForUser(@PathVariable String notiNo, HttpSession session, 
			ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("users");
		abilistsModel.setMenu("sltNotiForUserList");

		SltUserNotiPara sltUserNotiPara = new SltUserNotiPara();
		sltUserNotiPara.setNotiNo(notiNo);

		// Set user id
		this.handleSessionInfo(session, sltUserNotiPara);
		// Insert user notification
		notiService.insertUserNoti(sltUserNotiPara);

		// Select no-read notification
		List<NotificationJoinUserNotiModel> userNotiList = notiService.sltUserNotiList(sltUserNotiPara);

		// Set the count of the notification again
		session.setAttribute("notiCnt", userNotiList.size());

		SltNotiPara sltNotificationPara = new SltNotiPara();
		sltNotificationPara.setNotiNo(notiNo);
		
		// Execute the transaction
		abilistsModel.setNotification(notiService.sltNotification(sltNotificationPara));

		model.addAttribute("model", abilistsModel);

		return "noti/sltNotiForUser";
	}

	/*
	 * Notification by Ajax
	 */
	@RequestMapping(value="/sltNotiAjax")
	public @ResponseBody NotificationModel sltNotiAjax(@Valid @RequestBody SltNotiPara sltNotificationPara, 
			BindingResult bindingResult) throws Exception {

		// If it occurs a error, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("sltNotiAjax - it is occured a parameter error.");
			return null;
		}

		return notiService.sltNotification(sltNotificationPara);
	}

}