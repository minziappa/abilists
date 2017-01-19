package com.abilists.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.para.users.UpdateSettingsPara;
import com.abilists.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersAbilistsController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(UsersAbilistsController.class);

	@Autowired
	private MessageSource message;
	@Autowired
	private UsersService usersService;

	@RequestMapping(value = {"updatedSettings"})
	public String updatedSettings(@Valid UpdateSettingsPara updateSettingsPara, 
			BindingResult bindingResult, ModelMap model, 
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("users");
		abilistsModel.setMenu("updatedUsers");

		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("updatedSettings - it is occured a parameter error.");
			response.setStatus(400);
			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Execute the transaction
		if(!usersService.registerUser(updateSettingsPara)) {
			logger.error("updatedUsers - a registering user error occured.");
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "errors/error";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/users/settings";
	}


//	@RequestMapping(value = {"updatedUsers"})
//	public String updatedUsers(@Valid InputUsersInfoPara inputUsersInfoPara, 
//			BindingResult bindingResult, ModelMap model, 
//			HttpServletResponse response) throws Exception {
//
//		AbilistsModel abilistsModel = new AbilistsModel();
//		abilistsModel.setNavi("users");
//		abilistsModel.setMenu("updatedUsers");
//
//		Map<String, String> mapErrorMessage = null;
//
//		// If it occurs errors, set the default value.
//		if (bindingResult.hasErrors()) {
//			logger.error("updatedUsers - it is occured a parameter error.");
//			response.setStatus(400);
//			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
//			model.addAttribute("mapErrorMessage",  mapErrorMessage);
//			return "users/sltUsers";
//		}
//
//		// Execute the transaction
//		if(!usersService.registerUser(inputUsersInfoPara)) {
//			logger.error("updatedUsers - a registering user error occured.");
//			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
//			return "users/sltUsers";
//		}
//
//		return "redirect:/users/sltUsers";
//	}

}