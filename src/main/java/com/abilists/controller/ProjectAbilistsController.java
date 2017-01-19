package com.abilists.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.UserProjectsTechDetailBean;
import com.abilists.bean.model.MTechDetailModel;
import com.abilists.bean.model.UserProjectTechModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.para.admin.SltMTechDetailPara;
import com.abilists.bean.para.profile.MTechDetailListAjax;
import com.abilists.bean.para.users.DltProjectTechPara;
import com.abilists.bean.para.users.DltProjectsPara;
import com.abilists.bean.para.users.IstProjectTechPara;
import com.abilists.bean.para.users.IstProjectsPara;
import com.abilists.bean.para.users.SltProjectTechPara;
import com.abilists.bean.para.users.SltUserProjectPara;
import com.abilists.bean.para.users.UdtProjectTechPara;
import com.abilists.bean.para.users.UdtProjectsPara;
import com.abilists.common.bean.CommonBean;
import com.abilists.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/project")
public class ProjectAbilistsController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(ProjectAbilistsController.class);

	@Autowired
	private MessageSource message;
	@Autowired
	private UsersService usersService;
	@Autowired
	private CommonBean commonBean;

    @RequestMapping(value = {"sltProjectsList"})
	public String sltProjectsList(HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectsList");

		// Set user id
		SltUserProjectPara sltUserProjectPara = new SltUserProjectPara();
		this.handleSessionInfo(session, sltUserProjectPara);

		// Set Paging list
		int intSum = usersService.sltUserProjectsSum(sltUserProjectPara);
		abilistsModel.setPaging(usersService.makePaging(sltUserProjectPara, intSum));

		// Get user projects
		List<UserProjectsModel> userProjectsList = usersService.sltUserProjectsList(sltUserProjectPara);
		abilistsModel.setUserProjectsList(userProjectsList);
		model.addAttribute("model", abilistsModel);

		return "project/sltProjectsList";
	}

	/*
	 * Abilists > Tech
	 */
	@RequestMapping(value = {"sltProjectTechList"})
	public String sltProjectTechList(@Valid SltProjectTechPara sltProjectTechPara, 
			HttpSession session, ModelMap model) throws Exception {
	
		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectTechList");
	
		// Set user id
		this.handleSessionInfo(session, sltProjectTechPara);

		// Set Paging list
		int intSum = usersService.sltUserProjectTechSum(sltProjectTechPara);
		abilistsModel.setPaging(usersService.makePaging(sltProjectTechPara, intSum));

		// Get a tech list on a project.
		List<UserProjectTechModel> userProjectTechList = usersService.sltUserProjectTechList(sltProjectTechPara);
		abilistsModel.setUserProjectTechList(userProjectTechList);

		model.addAttribute("model", abilistsModel);

		return "project/viewProjectTechList";
	}

	@RequestMapping(value = {"sltProjectTechList/{upNo}"})
	public String sltProjectTechList(@PathVariable String upNo, 
			HttpSession session, ModelMap model) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectsList");

		// Get a project
		SltUserProjectPara sltUserProjectPara = new SltUserProjectPara();
		sltUserProjectPara.setUpNo(upNo);
		// Set user id
		this.handleSessionInfo(session, sltUserProjectPara);
		UserProjectsModel userProjects = usersService.sltUserProjects(sltUserProjectPara);
		abilistsModel.setUserProjects(userProjects);

		// Get a tech list on a project.
		SltProjectTechPara sltProjectTechPara = new SltProjectTechPara();
		sltProjectTechPara.setUpNo(upNo);
		// Set user id
		this.handleSessionInfo(session, sltProjectTechPara);
		List<UserProjectTechModel> userProjectTechList = usersService.sltUserProjectTechList(sltProjectTechPara);
		abilistsModel.setUserProjectTechList(userProjectTechList);
		model.addAttribute("model", abilistsModel);

		return "project/sltProjectTechList";
	}

	@RequestMapping(value="/sltProjectsAjax")
	public void sltProjectsAjax(@RequestParam("body") String body, HttpSession session, 
			HttpServletResponse response) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		JSONParser parser=new JSONParser();
		Object obj = parser.parse(body);
		JSONObject jsonObject = (JSONObject) obj;
		String upNo = (String)jsonObject.get("upNo");
	
		SltUserProjectPara sltUserProjectPara = new SltUserProjectPara();
		sltUserProjectPara.setUpNo(upNo);
	
		// Set user id
		this.handleSessionInfo(session, sltUserProjectPara);
		UserProjectsModel userProjects = usersService.sltUserProjects(sltUserProjectPara);
		Map<String, UserProjectsModel> map = new LinkedHashMap<String, UserProjectsModel>();
		map.put("result", userProjects);
	
		//Convert object to JSON string
		String jsonInString = mapper.writeValueAsString(map);
	
		this.handleWriteAjax(jsonInString, response);
	}

	@RequestMapping(value = {"sltProjectTechAjax"})
	public void sltUserProjectTechAjax(@RequestParam("body") String body,
			HttpSession session, HttpServletResponse response) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		JSONParser parser=new JSONParser();
		Object obj = parser.parse(body);
		JSONObject jsonObject = (JSONObject) obj;
		String uptNo = (String)jsonObject.get("uptNo");

		SltProjectTechPara sltProjectTechPara = new SltProjectTechPara();
		sltProjectTechPara.setUptNo(uptNo);
		// Set user id
		this.handleSessionInfo(session, sltProjectTechPara);

		// Get Techs of a project
		UserProjectTechModel userProjectTech = usersService.sltUserProjectTech(sltProjectTechPara);

		Map<String, UserProjectTechModel> map = new LinkedHashMap<String, UserProjectTechModel>();
		map.put("result", userProjectTech);

		//Convert object to JSON string
		String jsonInString = mapper.writeValueAsString(map);

		this.handleWriteAjax(jsonInString, response);
	}

//	@RequestMapping(value = {"sltProjectTechAjax"})
//	public void sltUserProjectTechAjax(@RequestParam("body") String body,
//			HttpSession session, HttpServletResponse response) throws Exception {
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		JSONParser parser=new JSONParser();
//		Object obj = parser.parse(body);
//		JSONObject jsonObject = (JSONObject) obj;
//		String uptNo = (String)jsonObject.get("uptNo");
//		String mtNo = (String)jsonObject.get("mtNo");
//
//		SltProjectTechPara sltProjectTechPara = new SltProjectTechPara();
//		sltProjectTechPara.setUptNo(uptNo);
//		// Set user id
//		this.handleSessionInfo(session, sltProjectTechPara);
//
//		// Get Techs of a project
//		UserProjectTechModel userProjectTech = usersService.sltUserProjectTech(sltProjectTechPara);
//
//		// TODO : Have to move to the business logic class
//		List<MTechDetailModel> mTechDetailList = commonBean.getmTechDetailMap().get(mtNo);
//		List<MTechDetailModel> langMTechDetailList = new ArrayList<>();
//		for(MTechDetailModel mTechDetail : mTechDetailList) {
//			if(sltProjectTechPara.getLang().equals(mTechDetail.getMlCode())) {
//
//				MTechDetailModel langMTechDetail = new MTechDetailModel();
//				langMTechDetail.setMtdNo(mTechDetail.getMtdNo());
//				langMTechDetail.setMtNo(mTechDetail.getMtNo());
//				langMTechDetail.setMtKind(mTechDetail.getMtKind());
//				langMTechDetail.setMtdLevel(mTechDetail.getMtdLevel());
//				langMTechDetail.setMtdLevelExplain(mTechDetail.getMtdLevelExplain());
//				langMTechDetail.setMlCode(mTechDetail.getMlCode());
//				langMTechDetail.setMtdStatus(mTechDetail.getMtdStatus());
//				langMTechDetail.setMtdDelete(mTechDetail.getMtdDelete());
//				langMTechDetail.setInsertTime(mTechDetail.getInsertTime());
//				langMTechDetail.setUpdateTime(mTechDetail.getUpdateTime());
//
//				logger.info(" >> " + mTechDetail.getMtdLevelExplain());
//				logger.info(" >> " + mTechDetail.getMlCode());
//				
//				langMTechDetailList.add(langMTechDetail);
//			}
//		}
//
//		UserProjectsTechDetailBean userProjectsTechDetail = new UserProjectsTechDetailBean();
//		userProjectsTechDetail.setUserProjectTech(userProjectTech);
//		userProjectsTechDetail.setmTechDetailList(langMTechDetailList);
//
//		Map<String, UserProjectsTechDetailBean> map = new LinkedHashMap<String, UserProjectsTechDetailBean>();
//		map.put("result", userProjectsTechDetail);
//
//		//Convert object to JSON string
//		String jsonInString = mapper.writeValueAsString(map);
//
//		this.handleWriteAjax(jsonInString, response);
//	}

	@RequestMapping(value = {"istProjects"})
	public String istProjects(@Valid IstProjectsPara istProjectsPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request, 
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectsList");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("istProjects - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);

			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), istProjectsPara);

		// Execute the transaction
		if(!usersService.insertUserProjects(istProjectsPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, LOCALE));
			return "errors/error";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/project/sltProjectsList";
	}

	@RequestMapping(value = {"istProjectTech"})
	public String istProjectTech(@Valid IstProjectTechPara istProjectTechPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectTechList");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("istProjectTech - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "project/sltProjectTechList";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), istProjectTechPara);

		// Execute the transaction
		if(!usersService.insertUserProjectTech(istProjectTechPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, locale));
			return "project/sltProjectTechList";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/project/sltProjectTechList/" + istProjectTechPara.getUpNo();
	}

	@RequestMapping(value = {"udtProjects"})
	public String udtProjects(@Valid UdtProjectsPara udtProjectsPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request, 
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectsList");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("udtProjects - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), udtProjectsPara);

		// Execute the transaction
		if(!usersService.updateUserProjects(udtProjectsPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, locale));
			return "errors/error";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/project/sltProjectsList";
	}

	@RequestMapping(value = {"udtProjectTech"})
	public String udtProjectTech(@Valid UdtProjectTechPara udtProjectTechPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectTechList");
		model.addAttribute("model", abilistsModel);

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("udtProjectTech - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), udtProjectTechPara);

		// Execute the transaction
		if(!usersService.updateUserProjectTech(udtProjectTechPara)) {
			mapErrorMessage = new HashMap<String, String>();
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.error.message", null, locale));
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/project/sltProjectTechList/" + udtProjectTechPara.getUpNo();
	}

	@RequestMapping(value = {"dltProjects"})
	public String dltProjects(@Valid DltProjectsPara dltProjectsPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request, 
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectsList");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("dltProjects - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user Id
		this.handleSessionInfo(request.getSession(), dltProjectsPara);

		// Execute the transaction
		if(!usersService.dltUserProjectsWithTech(dltProjectsPara)) {
			model.addAttribute("errorMessage", message.getMessage("parameter.error.message", null, locale));
			return "errors/error";
		}

		return "redirect:/project/sltProjectsList";
	}

	@RequestMapping(value = {"dltProjectTech"})
	public String dltProjectTech(@Valid DltProjectTechPara dltProjectTechPara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("project");
		abilistsModel.setMenu("sltProjectTechList");

		// Set language in Locale.
		Locale locale = RequestContextUtils.getLocale(request);

		Map<String, String> mapErrorMessage = null;
		// If it occurs errors, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("dltProjectTech - it is occured a parameter error.");
			response.setStatus(400);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Set user id
		this.handleSessionInfo(request.getSession(), dltProjectTechPara);

		// Execute the transaction
		if(!usersService.deleteUserProjectTech(dltProjectTechPara)) {
			mapErrorMessage = new HashMap<String, String>();
			mapErrorMessage.put("errorMessage", message.getMessage("parameter.update.error.message", null, locale));
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "errors/error";
		}

		// Pass the parameters with post.
		redirectAttributes.addFlashAttribute("save", "completed");
		return "redirect:/project/sltProjectTechList/" + dltProjectTechPara.getUpNo();
	}

}