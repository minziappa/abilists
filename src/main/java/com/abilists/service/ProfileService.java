package com.abilists.service;

import java.util.List;

import com.abilists.bean.model.UsersModel;
import com.abilists.bean.model.join.MIndustryJoinUserProjectsModel;
import com.abilists.bean.model.join.MTechJoinUserTechModel;
import com.abilists.bean.model.join.UserProjectsJoinTechModel;
import com.abilists.common.bean.CommonPara;

public interface ProfileService {

	public UsersModel sltUser(CommonPara commonPara) throws Exception;
	public List<MTechJoinUserTechModel> sltUserTechList(CommonPara commonPara) throws Exception;
	public List<MIndustryJoinUserProjectsModel> sltUserProjectsList(CommonPara commonPara) throws Exception;
	public List<UserProjectsJoinTechModel> sltUserProjectsAndTechList(CommonPara commonPara) throws Exception;

}
