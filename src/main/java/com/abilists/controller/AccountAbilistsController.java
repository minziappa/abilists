package com.abilists.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.model.UsersModel;
import com.abilists.bean.para.LoginPara;
import com.abilists.bean.para.account.UpdateEmail;
import com.abilists.bean.para.account.UdtInfoPara;
import com.abilists.bean.para.account.UdtPwd;
import com.abilists.common.bean.CommonBean;
import com.abilists.common.bean.CommonPara;
import com.abilists.service.AccountService;
import com.abilists.service.LoginService;

import io.utility.security.TokenUtility;

/*
 * Can edit user information.
 */
@SessionAttributes("myPicture")
@Controller
@RequestMapping("/account")
public class AccountAbilistsController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(AccountAbilistsController.class);

	@Autowired
	private AccountService accountService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private MessageSource message;
	@Autowired
	private CommonBean commonBean;
	@Autowired
    private Configuration configuration;

	/*
	 * Get user information to edit it
	 */
	@RequestMapping(value =  {"/", "", "/introInfo"})
	public String introInfo(ModelMap model, HttpSession session) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("account");
		abilistsModel.setMenu("settings");
		abilistsModel.setSidebar("introInfo");

		CommonPara commonPara = new CommonPara();
		// Set user id
		this.handleSessionInfo(session, commonPara);

		String token = TokenUtility.generateToken(TokenUtility.SHA_256);
		abilistsModel.setToken(token);
		commonBean.addTokenExpireMap(commonPara.getUserId(), token);

		UsersModel users = accountService.sltInfo(commonPara);
		abilistsModel.setUsers(users);

		model.addAttribute("model", abilistsModel);

		return "account/introInfo";
	}

	/*
	 * Update user information.
	 */
	@RequestMapping(value =  {"/udtInfo"})
	public String udtInfo(@Valid UdtInfoPara udtInfoPara, BindingResult bindingResult, 
			ModelMap model, HttpServletRequest request, HttpServletResponse response, 
			RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("account");
		abilistsModel.setMenu("settings");
		abilistsModel.setSidebar("introInfo");

		// Set user id
		this.handleSessionInfo(request.getSession(), udtInfoPara);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("udtInfo - it is occured a parameter error.");
			response.setStatus(400);
			Locale locale = RequestContextUtils.getLocale(request);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Get a security token by user id as key
		String tokenKey = commonBean.getTokenExpireMap(udtInfoPara.getUserId());
		// verify token 
		if(!udtInfoPara.getToken().equals(tokenKey)) {
			logger.error("the token is not same.");
			mapErrorMessage = new HashMap<String, String>();
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.update.error.message", null, LOCALE));
			model.addAttribute("mapErrorMessage",  mapErrorMessage);

			// Get key and token
			String token = TokenUtility.generateToken(TokenUtility.SHA_256);
			abilistsModel.setToken(token);
			commonBean.addTokenExpireMap(udtInfoPara.getUserId(), token);

			model.addAttribute("model", abilistsModel);
			return "account/introInfo";
		}

		// Update user information
		if(!accountService.updateInfo(udtInfoPara)) {
			logger.error("udtInfo is failed. userId=" + udtInfoPara.getUserId());
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "errors/error";
		}

		model.addAttribute("model", abilistsModel);
		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");

		return "redirect:/account/introInfo";
	}

	/*
	 * Upload user picture.
	 */
	@RequestMapping(value = {"introPicture"})
	public String introPicture(HttpSession session, ModelMap model, 
			RedirectAttributes redirectAttributes) throws Exception {

		String strImage = null;

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("account");
		abilistsModel.setMenu("settings");
		abilistsModel.setSidebar("introPicture");

		CommonPara commonPara = new CommonPara();
		// Set user id
		this.handleSessionInfo(session, commonPara);

		// Read image with byte
		strImage = this.handleReadImage(commonPara.getUserId(), configuration.getString("upload.path.img"));
		if(strImage == null) {
			return "account/introPicture";
		}
		model.addAttribute("myPicture", strImage);
		model.addAttribute("model", abilistsModel);

		return "account/introPicture";
	}

	/*
	 * Change user password
	 */
	@RequestMapping(value = {"introChangingPwd"})
	public String introChangingPwd(HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("account");
		abilistsModel.setMenu("settings");
		abilistsModel.setSidebar("introChangingPwd");

		// Set user id
		CommonPara commonPara = new CommonPara();
		this.handleSessionInfo(session, commonPara);

		// Generate token
		String token = TokenUtility.generateToken(TokenUtility.SHA_256);
		commonBean.addTokenExpireMap(commonPara.getUserId(), token);
		abilistsModel.setToken(token);

		model.addAttribute("model", abilistsModel);
		return "account/introChangingPwd";
	}

	/*
	 * Update user password
	 */
	@RequestMapping(value = {"udtChangingPwd"})
	public String udtChangingPwd(@Valid UdtPwd udtPassword, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("account");
		abilistsModel.setMenu("settings");
		abilistsModel.setSidebar("introChangingPwd");

		// Set user id from session
		this.handleSessionInfo(request.getSession(), udtPassword);

		Map<String, String> mapErrorMessage = null;
		// Validate parameters with annotation
		if (bindingResult.hasErrors()) {
			logger.error("udtChangingPwd - it is occured a parameter error.");

			// Generate a token to change user password.
			String token = TokenUtility.generateToken(TokenUtility.SHA_256);
			commonBean.addTokenExpireMap(udtPassword.getUserId(), token);
			abilistsModel.setToken(token);
			model.addAttribute("model", abilistsModel);

			Locale locale = RequestContextUtils.getLocale(request);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);

			return "account/introChangingPwd";
		}

		// Validate token
		if(!udtPassword.getToken().equals(commonBean.getTokenExpireMap(udtPassword.getUserId()))) {
			// Generate a token to change user password.
			String token = TokenUtility.generateToken(TokenUtility.SHA_256);
			commonBean.addTokenExpireMap(udtPassword.getUserId(), token);
			abilistsModel.setToken(token);
			model.addAttribute("model", abilistsModel);

			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "account/introChangingPwd";
		}

		// Update user password
		if(!accountService.updatedPwd(udtPassword)) {
			// Generate a token to change user password.
			String token = TokenUtility.generateToken(TokenUtility.SHA_256);
			commonBean.addTokenExpireMap(udtPassword.getUserId(), token);
			abilistsModel.setToken(token);
			model.addAttribute("model", abilistsModel);

			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "account/introChangingPwd";
		}

		model.addAttribute("model", abilistsModel);

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");

		return "redirect:/account/introChangingPwd";
	}

	/*
	 * Change user email.
	 */
	@RequestMapping(value = {"introChangingEmail"})
	public String introChangingEmail(HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("account");
		abilistsModel.setMenu("settings");
		abilistsModel.setSidebar("introChangingEmail");

		CommonPara commonPara = new CommonPara();
		this.handleSessionInfo(session, commonPara);

		// Generate a token
		String token = TokenUtility.generateToken(TokenUtility.SHA_256);
		commonBean.addTokenExpireMap(commonPara.getUserId(), token);
		abilistsModel.setToken(token);

		model.addAttribute("model", abilistsModel);
		return "account/introChangingEmail";
	}

	/*
	 * Update user email
	 */
	@RequestMapping(value = {"udtChangingEmail"})
	public String udtChangingEmail(@Valid UpdateEmail updateEmail, 
			BindingResult bindingResult, HttpSession session, ModelMap model, 
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("account");
		abilistsModel.setMenu("settings");
		abilistsModel.setSidebar("introChangingEmail");

		// Set user id from session
		this.handleSessionInfo(session, updateEmail);

		// Validate parameters with annotation
		if (bindingResult.hasErrors()) {
			logger.error("udtChangingEmail - it is occured a parameter error.");

			// Generate a token to change user password.
			String token = TokenUtility.generateToken(TokenUtility.SHA_256);
			commonBean.addTokenExpireMap(updateEmail.getUserId(), token);
			abilistsModel.setToken(token);
			model.addAttribute("model", abilistsModel);

			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(session, updateEmail);

		// Validate token
		if(!updateEmail.getToken().equals(commonBean.getTokenExpireMap(updateEmail.getUserId()))) {
			// Generate a token to change user password.
			String token = TokenUtility.generateToken(TokenUtility.SHA_256);
			commonBean.addTokenExpireMap(updateEmail.getUserId(), token);
			abilistsModel.setToken(token);
			model.addAttribute("model", abilistsModel);

			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "errors/error";
		}

		// Set parameters to validate current user password.
		LoginPara loginPara = new LoginPara();
		loginPara.setUserId(updateEmail.getUserId());
		loginPara.setUserPwd(updateEmail.getUserPwd());
		if(!loginService.validatePwd(loginPara)) {
			logger.error("validatePwd error. userId=" + loginPara.getUserId());
			// Generate a token to change user password.
			String token = TokenUtility.generateToken(TokenUtility.SHA_256);
			commonBean.addTokenExpireMap(loginPara.getUserId(), token);
			abilistsModel.setToken(token);
			model.addAttribute("model", abilistsModel);

			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "account/introChangingEmail";
		}

		// Update user email.
		if(!accountService.updatedEmail(updateEmail)) {
			logger.error("udtEmail error " + updateEmail.getUserId());

			// Generate a token to change user password.
			String token = TokenUtility.generateToken(TokenUtility.SHA_256);
			commonBean.addTokenExpireMap(updateEmail.getUserId(), token);
			abilistsModel.setToken(token);
			model.addAttribute("model", abilistsModel);

			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "account/introChangingEmail";
		}

		// Update the new email in session.
		UsersModel user = (UsersModel) session.getAttribute("user");
		user.setUserEmail(updateEmail.getNewUserEmail());
		session.setAttribute("user", user);

		model.addAttribute("model", abilistsModel);

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");

		return "redirect:/account/introChangingEmail";
	}

}