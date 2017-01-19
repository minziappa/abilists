package com.abilists.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * The <code>IndexController</code> class represents action controller.
 * 1. Explain for a method .....
 * 
 * @author  Joon Kim
 * @version 0.1, 15/09/01
 * @see     com.abilists.controller.IndexController#index()
 * @since   JDK1.7
 */
@Controller
public class IndexController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = {"/", "", "index"}, method=RequestMethod.GET)
	public String index(HttpSession session, ModelMap model) throws Exception {
	   	return "redirect:/home/";
	}

}