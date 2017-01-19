package com.abilists.service;

import java.util.List;

import com.abilists.bean.model.UserProjectTechModel;

public interface AdminService extends PagingService {

	public UserProjectTechModel sltUserProjectTech() throws Exception;
	public List<UserProjectTechModel> sltUserProjectTechList() throws Exception;
	
	public boolean reloadMaster() throws Exception;
}