package com.abilists.common.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.PassiveExpiringMap;

import com.abilists.bean.model.MIndustryModel;
import com.abilists.bean.model.MLanguageModel;
import com.abilists.bean.model.MRoleModel;
import com.abilists.bean.model.MTechDetailModel;
import com.abilists.bean.model.MTechModel;

public class CommonBean {

	HashMap<String, String> tokenMap = new HashMap<String, String>();

	PassiveExpiringMap<String, String> tokenExpireMap = new PassiveExpiringMap<String, String>(10, TimeUnit.MINUTES);

	HashMap<String, HashMap<String, List<MTechModel>>> mtechMap = new HashMap<String, HashMap<String, List<MTechModel>>>();

	HashMap<String, List<MTechModel>> mtechCertiMap = new HashMap<String, List<MTechModel>>();
	HashMap<String, List<MTechModel>> mtechLangMap = new HashMap<String, List<MTechModel>>();
	HashMap<String, List<MTechModel>> mtechSkillMap = new HashMap<String, List<MTechModel>>();
	HashMap<String, List<MTechModel>> mtechTechMap = new HashMap<String, List<MTechModel>>();
	HashMap<String, List<MTechDetailModel>> mTechDetailMap = new HashMap<String, List<MTechDetailModel>>();

	List<MRoleModel> mRoleList = new ArrayList<>();

	List<MLanguageModel> mLanguageList = new ArrayList<>();
	List<MIndustryModel> mIndustryList = new ArrayList<>();
	
	HashMap<String, List<MRoleModel>> mRoleMap = new HashMap<String, List<MRoleModel>>();
	HashMap<String, List<MIndustryModel>> mIndustryMap = new HashMap<String, List<MIndustryModel>>();

	public HashMap<String, List<MTechModel>> getMtechMap(String key) {
		return mtechMap.get(key);
	}

	public void addMtechMap (String key, HashMap<String, List<MTechModel>> valueMap) {
		this.mtechMap.put(key, valueMap);
	}

	public String getTokenMap(String key) {
		return tokenMap.get(key);
	}

	public void addTokenMap(String key, String value) {
		this.tokenMap.put(key, value);
	}

	public String getTokenExpireMap(String key) {
		return tokenExpireMap.get(key);
	}

	public void addTokenExpireMap(String key, String value) {
		this.tokenExpireMap.put(key, value);
	}

	public HashMap<String, List<MTechModel>> getMtechCertiMap() {
		return mtechCertiMap;
	}

	public void setMtechCertiMap(HashMap<String, List<MTechModel>> mtechCertiMap) {
		this.mtechCertiMap = mtechCertiMap;
	}

	public HashMap<String, List<MTechModel>> getMtechLangMap() {
		return mtechLangMap;
	}

	public void setMtechLangMap(HashMap<String, List<MTechModel>> mtechLangMap) {
		this.mtechLangMap = mtechLangMap;
	}

	public HashMap<String, List<MTechModel>> getMtechSkillMap() {
		return mtechSkillMap;
	}

	public void setMtechSkillMap(HashMap<String, List<MTechModel>> mtechSkillMap) {
		this.mtechSkillMap = mtechSkillMap;
	}

	public HashMap<String, List<MTechModel>> getMtechTechMap() {
		return mtechTechMap;
	}

	public void setMtechTechMap(HashMap<String, List<MTechModel>> mtechTechMap) {
		this.mtechTechMap = mtechTechMap;
	}

	public HashMap<String, List<MTechDetailModel>> getmTechDetailMap() {
		return mTechDetailMap;
	}

	public void setmTechDetailMap(HashMap<String, List<MTechDetailModel>> mTechDetailMap) {
		this.mTechDetailMap = mTechDetailMap;
	}

	public List<MRoleModel> getmRoleList() {
		return mRoleList;
	}

	public void setmRoleList(List<MRoleModel> mRoleList) {
		this.mRoleList = mRoleList;
	}

	public List<MLanguageModel> getmLanguageList() {
		return mLanguageList;
	}

	public void setmLanguageList(List<MLanguageModel> mLanguageList) {
		this.mLanguageList = mLanguageList;
	}

	public List<MIndustryModel> getmIndustryList() {
		return mIndustryList;
	}

	public void setmIndustryList(List<MIndustryModel> mIndustryList) {
		this.mIndustryList = mIndustryList;
	}

	public HashMap<String, List<MRoleModel>> getmRoleMap() {
		return mRoleMap;
	}

	public void setmRoleMap(HashMap<String, List<MRoleModel>> mRoleMap) {
		this.mRoleMap = mRoleMap;
	}

	public HashMap<String, List<MIndustryModel>> getmIndustryMap() {
		return mIndustryMap;
	}

	public void setmIndustryMap(HashMap<String, List<MIndustryModel>> mIndustryMap) {
		this.mIndustryMap = mIndustryMap;
	}

}
