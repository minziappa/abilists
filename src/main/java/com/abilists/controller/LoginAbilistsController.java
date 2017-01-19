package com.abilists.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.KeyTokenBean;
import com.abilists.bean.model.UserTemp;
import com.abilists.bean.model.UsersModel;
import com.abilists.bean.model.join.NotificationJoinUserNotiModel;
import com.abilists.bean.para.LoginPara;
import com.abilists.bean.para.login.CompleteConfirmPara;
import com.abilists.bean.para.login.CompleteResetPwdPara;
import com.abilists.bean.para.login.SignUpPara;
import com.abilists.bean.para.login.UserIdPara;
import com.abilists.common.bean.CommonBean;
import com.abilists.common.bean.CommonPara;
import com.abilists.service.LoginService;
import com.abilists.service.NotiService;
import com.abilists.service.ProfileService;

import io.utility.security.TokenUtility;

/**
 * Login and Logout
 * 
 * @author Joon
 *
 */
@SessionAttributes(value = {"commonBean", "userPicture"})
@Controller
@RequestMapping("/login")
public class LoginAbilistsController extends AbstractBaseController {

	final String TOKEN_KEY = "tokenKey";
	final Logger logger = LoggerFactory.getLogger(LoginAbilistsController.class);

	@Autowired
	private MessageSource message;
	@Autowired
	private LoginService loginService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private NotiService notiService;
	@Autowired
	private CommonBean commonBean;
	@Autowired
    private Configuration configuration;

	/**
	 * Login page and to register new
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = {"/", "", "index"}, method=RequestMethod.GET)
	public String index(HttpSession session, ModelMap model) throws Exception {
		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setNavi("abilists");

	   	model.addAttribute("model", abilistsModel);
		model.addAttribute("commonBean", commonBean);
		return "login/index";
	}

    /**
     * Check if there is already a user.
     * 
     * @param userIdPara
     * @param bindingResult
     * @return
     * @throws Exception
     */
	@RequestMapping(value = {"checkUserIdAjax"}, method = RequestMethod.POST)
	public @ResponseBody UserIdPara checkUserIdAjax(@Valid @RequestBody UserIdPara userIdPara,
			BindingResult bindingResult) throws Exception {

    	logger.info("checkUserIdAjax. userId=" + userIdPara.getUserId());

		// If it occurs a error, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("checkUserIdAjax - it is occured a parameter error.");
			userIdPara.setError("this is error");
			userIdPara.setResult("false");
			return userIdPara;
		}

		CommonPara commonPara = new CommonPara();
		commonPara.setUserId(userIdPara.getUserId());
    	// Skip if the parameter is error.
        UsersModel users = profileService.sltUser(commonPara);
        if(users != null) {
        	userIdPara.setResult("false");
        } else {
    		userIdPara.setResult("true");        	
        }

        return userIdPara;
	}

	/**
	 * To input a user id and password.
	 * 
	 * @param signUpPara
	 * @param bindingResult
	 * @param model
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"introSingup"})
	public String introSingup(@Valid SignUpPara signUpPara, BindingResult bindingResult, 
			ModelMap model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("abilists");

		// If it occurs a error, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("sendEmail - it is occured a parameter error.");
			response.setStatus(400);

			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Check if there is a user information in USER table.
		if(!loginService.validateEmail(signUpPara.getUserEmail())) {
			logger.error("confirmSingup - your email=" + signUpPara.getUserEmail());
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "errors/error";
		}

		UserTemp userTemp = loginService.sltUserTemp(signUpPara.getToken());
		if(userTemp != null) {
			logger.error("loginService.sltUserTemp - there is no temporary user data. userEmail=" + signUpPara.getUserEmail());
			model.addAttribute("errorMessage", "Reloaded");
			abilistsModel.setUserTemp(userTemp);
			model.addAttribute("model", abilistsModel);
			return "login/introSingup";
		}

		String token = TokenUtility.generateToken(TokenUtility.SHA_256);
		signUpPara.setToken(token);

		// Register a temporary user information
		signUpPara.setUserStatus("0"); // 0: to register
		loginService.insertUserTemp(signUpPara);

//		// Send an email to user by asynchronous
//		loginService.sendEmail(signUpPara);

		// Set a token at hidden of Input tag.
		userTemp = new UserTemp();
		userTemp.setUserTempToken(token);
		abilistsModel.setUserTemp(userTemp);

		model.addAttribute("model", abilistsModel);

		return "login/introSingup";
	}

//	@RequestMapping(value = {"introSingup"})
//	public String introSingup_backup(@Valid SignUpPara signUpPara, BindingResult bindingResult, 
//			ModelMap model,  HttpServletResponse response) throws Exception {
//
//		AbilistsModel abilistsModel = new AbilistsModel();
//
//		// If it occurs a error, set the default value.
//		if (bindingResult.hasErrors()) {
//			logger.error("introSingup - it is occured a parameter error. userEmail=" + signUpPara.getUserEmail());
//			response.setStatus(400);
//
//			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
//			model.addAttribute("mapErrorMessage",  mapErrorMessage);
//			model.addAttribute("model", abilistsModel);
//			return "errors/error";
//		}
//
//		UserTemp userTemp = loginService.sltUserTemp(signUpPara);
//		if(userTemp == null) {
//			logger.error("loginService.sltUserTemp - there is no temporary user data. userEmail=" + signUpPara.getUserEmail());
//			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
//			model.addAttribute("model", abilistsModel);
//			return "errors/error";
//		}
//
//		// loginService.g
//		abilistsModel.setUserTemp(userTemp);
//		model.addAttribute("model", abilistsModel);
//
//		return "login/introSingup";
//	}

	/**
	 * To complete registering new
	 * 
	 * @param completeConfirmPara
	 * @param bindingResult
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"completeSingup"})
	public String completeSingup(@Valid CompleteConfirmPara completeConfirmPara, 
			BindingResult bindingResult, ModelMap model, HttpSession session, HttpServletResponse response) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("abilists");

		// Custom Validate
		new LoginAbilistsValidator().validate(completeConfirmPara, bindingResult);
		// If it occurs a error, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("completeSingup - it is occured a parameter error.");
			response.setStatus(400);
			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);

			UserTemp userTemp = new UserTemp();
			userTemp.setUserTempToken(completeConfirmPara.getNtoken());
			abilistsModel.setUserTemp(userTemp);
			model.addAttribute("model", abilistsModel);

			return "login/introSingup";
		}

		// Completed user to register
		if(!loginService.completeRegister(completeConfirmPara)) {
			logger.error("Completing to register failed - ntoken=" + completeConfirmPara.getNtoken());
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "errors/error";
		}

		UsersModel user = profileService.sltUser(completeConfirmPara);
		if(user == null) {
			logger.error("There is no the account");
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "login/denied";
		}

		session.setAttribute("notiCnt", notiService.sltUserNotiSum(completeConfirmPara));
		session.setAttribute("user", user);
		logger.info("user status = " + user.getUserStatus());
		session.setMaxInactiveInterval(100*60);

		return "redirect:/abilists";
	}

	/**
	 * By email the link
	 * 
	 * @param token1
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"confirmSingup"})
	public String confirmSingup(@RequestParam("token1") String ntoken, ModelMap model) throws Exception {

		// slt a user from temp table after validating the token.
		UserTemp userTemp = loginService.sltUserTemp(ntoken);

		if(userTemp == null) {
			logger.error("there is no userTemp - token=" + ntoken);
			return "errors/error";	
		}

		CompleteConfirmPara completeConfirmPara = new CompleteConfirmPara();
		completeConfirmPara.setToken(ntoken);
		if(loginService.completeRegister(completeConfirmPara)) {
			logger.error("Complete to register error - token1=" + ntoken);
			return "errors/error";	
		}

		return "redirect:/abilists";
	}

//	@RequestMapping(value = {"completeConfirm"})
//	public String completeConfirm(@Valid CompleteConfirmPara completeConfirmPara, 
//			BindingResult bindingResult, ModelMap model, HttpServletResponse response) throws Exception {
//
//		AbilistsModel abilistsModel = new AbilistsModel();
//		abilistsModel.setNavi("abilists");
//
//		// Custom Validate
//		new LoginAbilistsValidator().validate(completeConfirmPara, bindingResult);
//		// If it occurs a error, set the default value.
//		if (bindingResult.hasErrors()) {
//			logger.error("completeConfirm - it is occured a parameter error.");
//			response.setStatus(400);
//			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
//			model.addAttribute("mapErrorMessage",  mapErrorMessage);
//
//			UserTemp userTemp = new UserTemp();
//			userTemp.setUserTempToken(completeConfirmPara.getToken());
//			abilistsModel.setUserTemp(userTemp);
//			model.addAttribute("model", abilistsModel);
//			return "errors/error";
//		}
//
//		loginService.completeRegister(completeConfirmPara);
//
//		model.addAttribute("model",  abilistsModel);
//
//		return "login/confirmSingup";
//	}

	/**
	 * The business logic for login.
	 * @param loginPara
	 * @param bindingResult
	 * @param response
	 * @param model
	 * @param session
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"login"})
	public String login(@Valid LoginPara loginPara, BindingResult bindingResult, 
			HttpServletResponse response, ModelMap model, HttpSession session, 
			RedirectAttributes redirectAttributes) throws Exception {

		String errorMessage = "";
		// session clear
		session.removeAttribute("user");
		session.removeAttribute("notiCnt");

		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("login - it is occured a parameter error.");
			response.setStatus(400);
			Locale locale = Locale.forLanguageTag(this.handleLang(session));
			// Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			// model.addAttribute("mapErrorMessage",  mapErrorMessage);
			errorMessage = message.getMessage("parameter.login.id.pwd.error.message", null, locale);
			model.addAttribute("errorMessage", errorMessage);
			return "login/index";
		}

		// Check if user has a right password
		if(!loginService.validatePwd(loginPara)) {
			logger.error("There is no the account or different password");
			Locale locale = Locale.forLanguageTag(this.handleLang(session));
			errorMessage = message.getMessage("parameter.login.id.pwd.error.message", null, locale);
			model.addAttribute("errorMessage", errorMessage);
			return "login/index";
		}

		CommonPara commonPara = new CommonPara();
		commonPara.setUserId(loginPara.getUserId());
		UsersModel usersModel = profileService.sltUser(commonPara);

		List<NotificationJoinUserNotiModel> userNotiList = notiService.sltUserNotiList(commonPara);

		session.setAttribute("notiCnt", userNotiList.size());
		session.setAttribute("userNotiList", userNotiList);
		session.setAttribute("user", usersModel);
		// Set user image into session
		session.setAttribute("myPicture", 
				this.handleReadImage(commonPara.getUserId(), configuration.getString("upload.path.img")));

		session.setMaxInactiveInterval(100*60);

		return "redirect:/abilists";
	}

	/**
	 * Log out
	 * 
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"logout"})
	public String logout(ModelMap model, HttpSession session) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();

		abilistsModel.setMenu("login");

		// Clear data in the session.
		session.invalidate();

		return "redirect:/";
	}

	@RequestMapping(value = {"introResetPwd"})
	public String introResetPwd(@Valid SignUpPara signUpPara, BindingResult bindingResult, 
			ModelMap model, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {

		KeyTokenBean keyToken = null;
		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("abilists");

		// If it occurs a error, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("introResetPwd - it is occured a parameter error.");
			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);

			// Get key and token
			keyToken = this.generateKeyAndToken();

			abilistsModel.setToken(keyToken.getToken());
			commonBean.addTokenExpireMap(keyToken.getKey(), keyToken.getToken());
			session.setAttribute(TOKEN_KEY, keyToken.getKey());
			model.addAttribute("model", abilistsModel);
			return "login/introResetPwd";
		}

		String tokenKey = commonBean.getTokenExpireMap((String)session.getAttribute(TOKEN_KEY));

		// verify token 
		if(!signUpPara.getToken().equals(tokenKey)) {
			logger.error("The token is not same. signUpPara.token=" + 
					signUpPara.getToken() + ", session.tokenKey=" + tokenKey);

			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);

			// Get key and token
			keyToken = this.generateKeyAndToken();
			abilistsModel.setToken(keyToken.getToken());
			commonBean.addTokenExpireMap(keyToken.getKey(), keyToken.getToken());
			session.setAttribute(TOKEN_KEY, keyToken.getKey());
			model.addAttribute("model", abilistsModel);
			return "login/introResetPwd";
		}

		// Check if there is a user information in USER table.
		// There is a user - false
		// None is user - true
		if(loginService.validateEmail(signUpPara.getUserEmail())) {
			logger.error("resetPassword - There is no your email address=" + signUpPara.getUserEmail());
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));

			// Get key and token
			keyToken = this.generateKeyAndToken();
			abilistsModel.setToken(keyToken.getToken());
			commonBean.addTokenExpireMap(keyToken.getKey(), keyToken.getToken());
			session.setAttribute(TOKEN_KEY, keyToken.getKey());
			model.addAttribute("model", abilistsModel);

			return "login/introResetPwd";
		}

		signUpPara.setUserStatus("1"); // 1: For reseting password
		// Register a temporary user information
		loginService.insertUserTemp(signUpPara);

		// Send an email to user by asynchronous
		loginService.sendEmailForResetPwd(signUpPara);

		return "redirect:/login/sendEmailResetPwd";
	}

	@RequestMapping(value = {"sendEmailResetPwd"})
	public String sendEmailResetPwd(ModelMap model, HttpSession session) throws Exception {

		return "login/sendEmailResetPwd";
	}

	@RequestMapping(value = {"resetPwd"})
	public String resetPwd(@RequestParam("ntoken") String ntoken, ModelMap model, 
			HttpSession session) throws Exception {

		KeyTokenBean keyToken = null;
		AbilistsModel abilistsModel = new AbilistsModel();

		// slt a user from temp table after validating the token.
		UserTemp userTemp = loginService.sltUserTemp(ntoken);

		if(userTemp == null) {
			logger.error("there is no userTemp - token=" + ntoken);
			return "errors/error";	
		}

		// Get key and token
		keyToken = this.generateKeyAndToken();
		abilistsModel.setToken(keyToken.getToken());
		commonBean.addTokenExpireMap(keyToken.getKey(), keyToken.getToken());
		session.setAttribute(TOKEN_KEY, keyToken.getKey());
		model.addAttribute("model", abilistsModel);
		model.addAttribute("ntoken", ntoken);

		return "login/resetPwd";
	}

	@RequestMapping(value = {"completeResetPwd"})
	public String completeResetPwd(@Valid CompleteResetPwdPara completeResetPwdPara, 
			BindingResult bindingResult, ModelMap model, HttpSession session, HttpServletResponse response) throws Exception {

		KeyTokenBean keyToken = null;
		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("abilists");

		// If it occurs a error, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("completeResetPwd - it is occured a parameter error.");
			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);

			// Get key and token
			keyToken = this.generateKeyAndToken();

			abilistsModel.setToken(keyToken.getToken());
			commonBean.addTokenExpireMap(keyToken.getKey(), keyToken.getToken());
			session.setAttribute(TOKEN_KEY, keyToken.getKey());
			model.addAttribute("model", abilistsModel);
			model.addAttribute("ntoken", completeResetPwdPara.getNtoken());
			return "login/resetPwd";
		}

		String token = commonBean.getTokenExpireMap((String)session.getAttribute(TOKEN_KEY));

		// verify token 
		if(!completeResetPwdPara.getToken().equals(token)) {
			logger.error("The token is not same. completeResetPwdPara.token=" + 
					completeResetPwdPara.getToken() + ", session.token=" + token);

			Map<String, String> mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors());
			model.addAttribute("mapErrorMessage",  mapErrorMessage);

			// Get key and token
			keyToken = this.generateKeyAndToken();
			abilistsModel.setToken(keyToken.getToken());
			commonBean.addTokenExpireMap(keyToken.getKey(), keyToken.getToken());
			session.setAttribute(TOKEN_KEY, keyToken.getKey());
			model.addAttribute("model", abilistsModel);
			model.addAttribute("ntoken", completeResetPwdPara.getNtoken());
			return "login/resetPwd";
		}

		if(!loginService.updatePwd(completeResetPwdPara)) {
			logger.error("Completing to register failed - token=" + completeResetPwdPara.getNtoken());
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "errors/error";
		}

		return "redirect:/login";
	}

	@RequestMapping(value = {"denied"})
	public String denied(ModelMap model, HttpSession session) throws Exception {

		return "login/denied";
	}

}