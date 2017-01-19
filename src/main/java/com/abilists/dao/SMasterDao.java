package com.abilists.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abilists.bean.model.MIndustryModel;
import com.abilists.bean.model.MLanguageModel;
import com.abilists.bean.model.MRoleModel;
import com.abilists.bean.model.MTechDetailModel;
import com.abilists.bean.model.MTechModel;

@Repository
public interface SMasterDao {
	public MRoleModel sltMRole(Map<String, Object> map) throws SQLException;
	public MTechModel sltMTech(Map<String, Object> map) throws SQLException;
	public MTechDetailModel sltMTechDetail(Map<String, Object> map) throws SQLException;

	public MIndustryModel sltMIndustry(Map<String, Object> map) throws SQLException;
	public List<MRoleModel> sltMRoleList(Map<String, Object> map) throws SQLException;
	public List<MTechModel> sltMTechList(Map<String, Object> map) throws SQLException;
	public List<MTechDetailModel> sltMTechDetailList(Map<String, Object> map) throws SQLException;

	public List<MIndustryModel> sltMIndustryList(Map<String, Object> map) throws SQLException;
	public List<MTechModel> sltMTechAll() throws SQLException;
	public List<MTechDetailModel> sltMTechDetailAll() throws SQLException;

	public List<MRoleModel> sltMRoleAll() throws SQLException;
	public List<MIndustryModel> sltMIndustryAll() throws SQLException;
	public List<MLanguageModel> sltMLanguageAll() throws SQLException;
	public int sltMTechSum(Map<String, Object> map) throws SQLException;
	public int sltMRoleSum(Map<String, Object> map) throws SQLException;
	public int sltMIndustrySum(Map<String, Object> map) throws SQLException;
	public int sltMNotiSum() throws SQLException;

}