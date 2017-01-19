package com.abilists.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abilists.bean.AbilistsModel;

@Controller
@RequestMapping("/home")
public class HomeController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = {"/", "", "index"}, method=RequestMethod.GET)
	public String index(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("index");

	   	model.addAttribute("model", abilistsModel);
		return "index";
	}

	@RequestMapping(value = {"/{download}"})
	public String download(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("abilists");

	   	model.addAttribute("model", abilistsModel);

		return "home/download";
	}

    @RequestMapping(value = {"products"}, method=RequestMethod.GET)
	public String products(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("index");

	   	model.addAttribute("model", abilistsModel);

		return "home/products";
	}

    @RequestMapping(value = {"getstart"}, method=RequestMethod.GET)
	public String getStart(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("index");

	   	model.addAttribute("model", abilistsModel);

		return "home/getstart";
	}

    @RequestMapping(value = {"solustions"}, method=RequestMethod.GET)
	public String solustions(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("index");

	   	model.addAttribute("model", abilistsModel);

		return "home/solustions";
	}

    @RequestMapping(value = {"customers"}, method=RequestMethod.GET)
	public String customers(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("index");

	   	model.addAttribute("model", abilistsModel);

		return "home/customers";
	}

}