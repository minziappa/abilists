package com.abilists.service;

import java.util.List;

import com.abilists.bean.model.UserProjectTechModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.para.users.SltUserProjectPara;
import com.abilists.bean.para.users.SltProjectTechPara;
import com.abilists.common.bean.CommonPara;

public interface AdminProjectsService extends PagingService {

	public List<UserTaskModel> sltUserTaskList(CommonPara commonPara) throws Exception;
	public List<UserProjectsModel> sltUserProjectsList(SltUserProjectPara sltUserProjectPara) throws Exception;
	public List<UserProjectTechModel> sltUserProjectTechList(SltProjectTechPara sltUserProjectTechPara) throws Exception;

	public UserProjectsModel sltUserProjects(SltUserProjectPara sltUserProjectPara) throws Exception;
	public UserProjectTechModel sltUserProjectTech(SltProjectTechPara sltUserProjectTechPara) throws Exception;

	public int sltUserTaskSum() throws Exception;
	public int sltUserProjectsSum(SltUserProjectPara sltUserProjectPara) throws Exception;
	public int sltUserProjectTechSum(SltProjectTechPara sltUserProjectTechPara) throws Exception;
}