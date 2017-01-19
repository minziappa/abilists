package com.abilists.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abilists.bean.model.MIndustryModel;
import com.abilists.bean.model.MLanguageModel;
import com.abilists.bean.model.MRoleModel;
import com.abilists.bean.model.MTechDetailModel;
import com.abilists.bean.model.MTechModel;
import com.abilists.common.bean.CommonBean;

@Service
public class StartStopService {

	final Logger logger = LoggerFactory.getLogger(StartStopService.class);
	
	@Autowired
	private AdminMasterService adminMasterService;
	@Autowired
	private CommonBean commonBean;

	private void loadMTechAll() throws Exception {

		List<MTechModel> mtechList = adminMasterService.sltMTechAll();

		HashMap<String, List<MTechModel>> mtechCertiMap = new HashMap<String, List<MTechModel>>();
		HashMap<String, List<MTechModel>> mtechLangMap = new HashMap<String, List<MTechModel>>();
		HashMap<String, List<MTechModel>> mtechSkillMap = new HashMap<String, List<MTechModel>>();

		List<MTechModel> newMTechCertiList = null;
		List<MTechModel> newMTechLangList = null;
		List<MTechModel> newMTechSkillList = null;

		String mtKindCerti = "";
		String mtKindLang = "";
		String mtKindSkill = "";

		for(MTechModel mtech : mtechList) {
			if(mtech.getMtSkill().equals("certi")) {
				if(!mtKindCerti.equals(mtech.getMtKind())) {
					if(mtKindCerti.length() > 0) {
						mtechCertiMap.put(mtKindCerti, newMTechCertiList);
					}
					mtKindCerti = mtech.getMtKind();
					newMTechCertiList = new ArrayList<MTechModel>();
				}
				MTechModel newMTech = new MTechModel();
				newMTech.setMtNo(mtech.getMtNo());
				newMTech.setMtName(mtech.getMtName());
				newMTech.setMtVersion(mtech.getMtVersion());
				newMTech.setMtExplain(mtech.getMtExplain());
				newMTechCertiList.add(newMTech);

			} else if(mtech.getMtSkill().equals("lang")) {

				if(!mtKindLang.equals(mtech.getMtKind())) {
					if(mtKindLang.length() > 0) {
						mtechLangMap.put(mtKindLang, newMTechLangList);
					}
					mtKindLang = mtech.getMtKind();
					newMTechLangList = new ArrayList<MTechModel>();
				}
				MTechModel newMTech = new MTechModel();
				newMTech.setMtNo(mtech.getMtNo());
				newMTech.setMtName(mtech.getMtName());
				newMTech.setMtVersion(mtech.getMtVersion());
				newMTech.setMtExplain(mtech.getMtExplain());
				newMTechLangList.add(newMTech);

			} else if(mtech.getMtSkill().equals("skills")) {

				if(!mtKindSkill.equals(mtech.getMtKind())) {
					if(mtKindSkill.length() > 0) {
						mtechSkillMap.put(mtKindSkill, newMTechSkillList);
					}
					mtKindSkill = mtech.getMtKind();
					newMTechSkillList = new ArrayList<MTechModel>();
				}
				MTechModel newMTech = new MTechModel();
				newMTech.setMtNo(mtech.getMtNo());
				newMTech.setMtName(mtech.getMtName());
				newMTech.setMtVersion(mtech.getMtVersion());
				newMTech.setMtExplain(mtech.getMtExplain());
				newMTechSkillList.add(newMTech);
			} else {
				logger.warn("Need to check data. it's not possible.");
			}
		}

		// certi
		mtechCertiMap.put(mtKindCerti, newMTechCertiList);
//		commonBean.setMtechCertiMap(mtechCertiMap);
		// lang
		mtechLangMap.put(mtKindLang, newMTechLangList);
//		commonBean.setMtechLangMap(mtechLangMap);
		// skills
		mtechSkillMap.put(mtKindSkill, newMTechSkillList);
//		commonBean.setMtechSkillMap(mtechSkillMap);

		commonBean.addMtechMap("certi", mtechCertiMap);
		commonBean.addMtechMap("lang", mtechLangMap);
		commonBean.addMtechMap("skills", mtechSkillMap);

//		for( String key : mtechSkillMap.keySet() ){
//			logger.info("key >>> " + key);
//			for(MTechModel mtech : mtechSkillMap.get(key)) {
//				logger.info("mtNo=" + mtech.getMtNo() + ", mtName=" + mtech.getMtName());
//			}
//        }

	}

	private void loadMTechDetailAll() throws Exception {

		List<MTechDetailModel> mTechDetailList = adminMasterService.sltMTechDetailAll();

		HashMap<String, List<MTechDetailModel>> mTechDetailMap = new HashMap<String, List<MTechDetailModel>>();

		List<MTechDetailModel> newMTechDetailList = null;
		String mtNo = "";
		for(MTechDetailModel mTechDetail : mTechDetailList) {
			if(!mtNo.equals(String.valueOf(mTechDetail.getMtNo()))) {
				if(mtNo.length() > 0) {
					mTechDetailMap.put(mtNo, newMTechDetailList);
				}
				mtNo = String.valueOf(mTechDetail.getMtNo());
				newMTechDetailList = new ArrayList<MTechDetailModel>();
			}
			MTechDetailModel newMTechDetail = new MTechDetailModel();
			newMTechDetail.setMtNo(mTechDetail.getMtNo());
			newMTechDetail.setMtdLevel(mTechDetail.getMtdLevel());
			newMTechDetail.setMtdLevelExplain(mTechDetail.getMtdLevelExplain());
			newMTechDetail.setMlCode(mTechDetail.getMlCode());
			newMTechDetail.setMtdNo(mTechDetail.getMtdNo());
			newMTechDetailList.add(newMTechDetail);
		}
		mTechDetailMap.put(mtNo, newMTechDetailList);

		commonBean.setmTechDetailMap(mTechDetailMap);

//		for( String key : mTechDetailMap.keySet() ){
//			logger.info("key >>> " + key);
//			List<MTechDetailModel> amTechDetailList = mTechDetailMap.get(key);
//			for(MTechDetailModel mTechDetail : amTechDetailList) {
//				logger.info("MtNo() >> " + mTechDetail.getMtNo());
//				logger.info("MlCode() >> " + mTechDetail.getMlCode());
//				logger.info("MtdLevelExplain() >> " + mTechDetail.getMtdLevelExplain());
//			}
//        }

	}

//	private void loadRollAll() throws Exception {
//		List<MRoleModel> mRoleList = adminMasterService.sltMRoleAll();
//		commonBean.setmRoleList(mRoleList);
//		for(MRoleModel mRole: mRoleList) {
//			logger.info("mrName >> " + mRole.getMrName());
//		}
//	}

	private void loadRollAll() throws Exception {

		List<MRoleModel> mRoleList = adminMasterService.sltMRoleAll();

		HashMap<String, List<MRoleModel>> mRoleMap = new HashMap<String, List<MRoleModel>>();

		List<MRoleModel> newMRoleList = null;
		String mlCode = "";
		for(MRoleModel mRole : mRoleList) {
			if(!mlCode.equals(mRole.getMlCode())) {
				if(mlCode.length() > 0) {
					mRoleMap.put(mlCode, newMRoleList);
				}
				mlCode = mRole.getMlCode();
				newMRoleList = new ArrayList<MRoleModel>();
			}
			MRoleModel newMRole = new MRoleModel();
			newMRole.setMrNo(mRole.getMrNo());
			newMRole.setMrName(mRole.getMrName());
			newMRole.setMrCode(mRole.getMrCode());
			newMRole.setMlCode(mRole.getMlCode());
			newMRole.setMrStatus(mRole.getMrStatus());
			newMRoleList.add(newMRole);
		}
		mRoleMap.put(mlCode, newMRoleList);

		commonBean.setmRoleMap(mRoleMap);

//		for( String key : mRoleMap.keySet() ){
//			logger.info("key >>> " + key);
//			for(MRoleModel mRole : mRoleMap.get(key)) {
//				logger.info("mRole : " + mRole.getMrNo() + ", mrName :" + mRole.getMrName());
//			}
//        }

	}

	private void loadMLanguageAll() throws Exception {
		List<MLanguageModel> mLanguageList = adminMasterService.sltMLanguageAll();
		commonBean.setmLanguageList(mLanguageList);
//		for(MLanguageModel mLanguage: mLanguageList) {
//			logger.info("mlName >> " + mLanguage.getMlName());
//		}
	}

	private void loadMIndustryAll() throws Exception {

		List<MIndustryModel> mIndustryList = adminMasterService.sltMIndustryAll();

		HashMap<String, List<MIndustryModel>> mIndustryMap = new HashMap<String, List<MIndustryModel>>();

		List<MIndustryModel> newMIndustryList = null;
		String mlCode = "";
		for(MIndustryModel mIndustry : mIndustryList) {
			if(!mlCode.equals(mIndustry.getMlCode())) {
				if(mlCode.length() > 0) {
					mIndustryMap.put(mlCode, newMIndustryList);
				}
				mlCode = mIndustry.getMlCode();
				newMIndustryList = new ArrayList<MIndustryModel>();
			}
			MIndustryModel newMIndustry = new MIndustryModel();
			newMIndustry.setMiNo(mIndustry.getMiNo());
			newMIndustry.setMiLargeCategory(mIndustry.getMiLargeCategory());
			newMIndustry.setMiMiddleCategory(mIndustry.getMiMiddleCategory());
			newMIndustry.setMiCode(mIndustry.getMiCode());
			newMIndustryList.add(newMIndustry);
		}
		mIndustryMap.put(mlCode, newMIndustryList);

		commonBean.setmIndustryMap(mIndustryMap);

//		for( String key : mIndustryMap.keySet() ){
//			logger.info("key >>> " + key);
//			for(MIndustryModel mIndustry : mIndustryMap.get(key)) {
//				logger.info("miLargeCategory >> " + mIndustry.getMiLargeCategory());
//			}
//        }

	}

	/**
	 * TODO
	 * Must to improve this logic, it doesn't do extra coding job for adding a master data.
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {

		this.loadMTechAll();
		this.loadRollAll();
		this.loadMLanguageAll();
		this.loadMIndustryAll();
		this.loadMTechDetailAll();
	}

	public void stop() {
		logger.info("This is stop");
	}

}
