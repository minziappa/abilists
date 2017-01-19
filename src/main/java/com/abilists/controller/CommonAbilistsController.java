package com.abilists.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abilists.bean.model.MTechDetailModel;
import com.abilists.bean.para.admin.SltMTechDetailPara;
import com.abilists.bean.para.profile.MTechDetailListAjax;
import com.abilists.service.AdminMasterService;

public class CommonAbilistsController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(CommonAbilistsController.class);

	@Autowired
	private AdminMasterService adminMasterService;

	@RequestMapping(value = {"sltMTechDetailListAjax"}, method = RequestMethod.POST)
	public @ResponseBody MTechDetailListAjax sltMTechDetailListAjax(
			@RequestBody SltMTechDetailPara sltMTechDetailPara, 
			HttpSession session) throws Exception {

    	logger.info("sltMTechDetailListAjax. MtNo >> " + sltMTechDetailPara.getMtNo());

		// Set user id
		this.handleSessionInfo(session, sltMTechDetailPara);
		// Set language
		sltMTechDetailPara.setMlCode(sltMTechDetailPara.getLang());

		// Get tech list in detail.
		List<MTechDetailModel> mTechDetailList = adminMasterService.sltMTechDetailList(sltMTechDetailPara);
    	logger.info("mTechDetailList size = " + mTechDetailList.size());

		MTechDetailListAjax mTechDetailListAjax = new MTechDetailListAjax();
		mTechDetailListAjax.setmTechDetailList(mTechDetailList);

        return mTechDetailListAjax;
	}

}