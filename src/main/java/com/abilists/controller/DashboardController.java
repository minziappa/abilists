package com.abilists.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abilists.bean.AbilistsModel;

/**
 * Not used but maybe it will be used
 * 
 * @author njoonk
 *
 */
@Controller
@RequestMapping("/dash")
public class DashboardController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @RequestMapping(value = {"/", "", "index"}, method=RequestMethod.GET)
	public String index(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("index");

	   	model.addAttribute("model", abilistsModel);

		return "dash/index";
	}

}