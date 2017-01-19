package com.abilists.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abilists.bean.model.join.MIndustryJoinUserProjectsModel;
import com.abilists.bean.model.join.MTechJoinUserTechModel;
import com.abilists.bean.model.join.UserProjectsJoinTechModel;

@Repository
public interface SJoinDao {

	public List<MTechJoinUserTechModel> sltMTechJoinUserTechList(Map<String, Object> map) throws SQLException;
	public List<MIndustryJoinUserProjectsModel> sltMIndustryJoinUserProjectsList(Map<String, Object> map) throws SQLException;
	public List<UserProjectsJoinTechModel> sltUserProjectsJoinTechList(Map<String, Object> map) throws SQLException;

}