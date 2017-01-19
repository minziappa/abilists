package com.abilists.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.model.UserTechModel;
import com.abilists.bean.model.UsersModel;
import com.abilists.bean.model.join.MIndustryJoinUserProjectsModel;
import com.abilists.bean.model.join.MTechJoinUserTechModel;
import com.abilists.bean.model.join.UserProjectsJoinTechModel;
import com.abilists.bean.para.users.SltUserTechPara;
import com.abilists.bean.para.users.UdtUserTechPara;
import com.abilists.common.bean.CommonPara;
import com.abilists.service.AdminMasterService;
import com.abilists.service.ProfileService;
import com.abilists.service.UsersService;

@Controller
@RequestMapping("/profile")
public class ProfileAbilistsController extends CommonAbilistsController {

	final Logger logger = LoggerFactory.getLogger(ProfileAbilistsController.class);
	@Autowired
	private MessageSource message;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private AdminMasterService adminMasterService;
	@Autowired
	private UsersService usersService;
	@Autowired
    private Configuration configuration;

	private AbilistsModel getUserData(CommonPara commonPara, AbilistsModel abilistsModel) throws Exception {

		// User information in profile
		UsersModel users = profileService.sltUser(commonPara);
		abilistsModel.setUsers(users);

		// User skills in profile.
		List<MTechJoinUserTechModel> mTechJoinUserTechList = profileService.sltUserTechList(commonPara);
		abilistsModel.setmTechJoinUserTechList(mTechJoinUserTechList);

		// User position in some industry.
		List<MIndustryJoinUserProjectsModel> mIndustryJoinUserProjectsList = profileService.sltUserProjectsList(commonPara);
		abilistsModel.setmIndustryJoinUserProjectsList(mIndustryJoinUserProjectsList);

		// User skills count in projects.
		List<UserProjectsJoinTechModel> userProjectsJoinTechList = profileService.sltUserProjectsAndTechList(commonPara);
		abilistsModel.setUserProjectsJoinTechList(userProjectsJoinTechList);
		
		return abilistsModel;
	}

	// TODO : 
	@RequestMapping(value =  {"/", "", "index"})
	public String myProfile(ModelMap model, HttpSession session) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("profile");
		abilistsModel.setMenu("profile");

		CommonPara commonPara = new CommonPara();

		// Set user id
		this.handleSessionInfo(session, commonPara);

//		// Read image with byte
//		model.addAttribute("userPicture", 
//				this.handleReadImage(commonPara.getUserId(), configuration.getString("upload.path.img")));
//
//		// Get user result of aggregate
//		model.addAttribute("model", getUserData(commonPara, abilistsModel));

		return this.profile(commonPara.getUserId(), session, model);
	}

	// TODO : Have to make sub page for user technologies
	@RequestMapping(value =  {"/{userId}"})
	public String profile(@PathVariable String userId, HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("profile");
		abilistsModel.setMenu("profile");

		CommonPara commonPara = new CommonPara();
		commonPara.setUserId(userId);

		// Set user language
		this.handleLang(session, commonPara);

		// Read image with byte
		model.addAttribute("userPicture", 
				this.handleReadImage(commonPara.getUserId(), configuration.getString("upload.path.img")));

		model.addAttribute("model", getUserData(commonPara, abilistsModel));

		return "profile/profile";
	}

	// TODO : Have to make sub page for user technologies
	@RequestMapping(value =  {"/{userId}/resume"})
	public String resume(@PathVariable String userId, HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("profile");
		abilistsModel.setMenu("resume");

		CommonPara commonPara = new CommonPara();

		// Set user id
		this.handleSessionInfo(session, commonPara);

		// Read image with byte
		model.addAttribute("userPicture", 
				this.handleReadImage(commonPara.getUserId(), configuration.getString("upload.path.img")));

		model.addAttribute("model", getUserData(commonPara, abilistsModel));
		
		return "profile/resume";
	}

	/**
	 * slt user tech information
	 * 
	 * @param utSkill
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"sltMyTechList/{utSkill}"})
	public String sltMyTechList(@PathVariable String utSkill, HttpSession session, 
			ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("profile");
		abilistsModel.setMenu(utSkill);

		// Validate in business
		boolean blnValidate = new ProjectAbilistsValidator().validateSimple(utSkill, configuration);
		// If it occurs a error, redirect to the default URL
		if (!blnValidate) {
			logger.error("sltUserTechList - the path is wrong. skill=" + utSkill);
			return "redirect:/";
		}

		// Set user id
		SltUserTechPara sltUserTechPara = new SltUserTechPara();
		this.handleSessionInfo(session, sltUserTechPara);
		sltUserTechPara.setUtSkill(utSkill);

		List<UserTechModel> userTechList = usersService.sltUserTechList(sltUserTechPara);
		abilistsModel.setUserTechList(userTechList);
		model.addAttribute("model", abilistsModel);

		return "profile/sltMyTechList/myTech";
	}

	@RequestMapping(value = {"udtUserTech"})
	public String udtUserTech(@Valid UdtUserTechPara udtUserTechPara, 
			BindingResult bindingResult, ModelMap model, HttpSession session,  
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("profile");
		abilistsModel.setMenu(udtUserTechPara.getUtSkill());

		// Validate in business
		boolean blnValidate = new ProjectAbilistsValidator().validateSimple(udtUserTechPara.getUtSkill(), configuration);
		// If it occurs a error, redirect to the default URL
		if (!blnValidate) {
			logger.error("updatedUserTech - the path is wrong. skill=" + udtUserTechPara.getUtSkill());
			return "redirect:/";
		}

		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("updatedUserProjects - it is occured a parameter error.");
			response.setStatus(400);
			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(session, udtUserTechPara);

		// Execute the transaction
		if(!usersService.updateUserTech(udtUserTechPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "users/sltUserTechList/userTech";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/profile/sltMyTechList/" + udtUserTechPara.getUtSkill();
	}

	@RequestMapping(value =  {"/{userId}/{skills}"})
	public String profileSkills(@PathVariable String userId, @PathVariable String skills, 
			HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("profile");
		abilistsModel.setMenu("tech");

		CommonPara commonPara = new CommonPara();
		commonPara.setUserId(userId);

		// Read image with byte
		model.addAttribute("userPicture", 
				this.handleReadImage(commonPara.getUserId(), configuration.getString("upload.path.img")));

		model.addAttribute("model", getUserData(commonPara, abilistsModel));

		return "profile/tech";
	}

}