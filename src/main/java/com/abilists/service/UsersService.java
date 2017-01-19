package com.abilists.service;

import java.util.List;

import com.abilists.bean.model.UserProjectTechModel;
import com.abilists.bean.model.UserProjectsModel;
import com.abilists.bean.model.UserTechModel;
import com.abilists.bean.model.sum.UserProjectsSumByYearModel;
import com.abilists.bean.para.users.DltProjectTechPara;
import com.abilists.bean.para.users.DltProjectsPara;
import com.abilists.bean.para.users.InputUsersInfoPara;
import com.abilists.bean.para.users.IstProjectTechPara;
import com.abilists.bean.para.users.IstProjectsPara;
import com.abilists.bean.para.users.SltUserProjectPara;
import com.abilists.bean.para.users.SltProjectTechPara;
import com.abilists.bean.para.users.SltUserTechPara;
import com.abilists.bean.para.users.UdtProjectsPara;
import com.abilists.bean.para.users.UpdateSettingsPara;
import com.abilists.bean.para.users.UdtProjectTechPara;
import com.abilists.bean.para.users.UdtUserTechPara;
import com.abilists.common.bean.CommonPara;

public interface UsersService extends PagingService {

	// public UsersModel sltUser(CommonPara commonPara) throws Exception;

	public boolean registerUser(UpdateSettingsPara updateSettingsPara) throws Exception;

	public List<UserTechModel> sltUserTechList(SltUserTechPara sltUserTechPara) throws Exception;

	public int sltUserProjectsSum(SltUserProjectPara sltUserProjectPara) throws Exception;
	public UserProjectsModel sltUserProjects(SltUserProjectPara sltUserProjectPara) throws Exception;
	public List<UserProjectsModel> sltUserProjectsList(SltUserProjectPara sltUserProjectPara) throws Exception;
	public List<UserProjectsModel> searchUserProjectsList(UdtProjectsPara inputUserProjectsPara) throws Exception;

	// Aggregate for user information
	public List<UserProjectsSumByYearModel> sltUserProjectsSumByYearList(CommonPara commonPara) throws Exception;

	public int sltUserProjectTechSum(SltProjectTechPara sltUserProjectTechPara) throws Exception;
	public UserProjectTechModel sltUserProjectTech(SltProjectTechPara sltUserProjectTechPara) throws Exception;
	public List<UserProjectTechModel> sltUserProjectTechList(SltProjectTechPara sltUserProjectTechPara) throws Exception;

	public boolean insertUsers(InputUsersInfoPara inputUsersInfoPara) throws Exception;
	public boolean insertUserProjects(IstProjectsPara insertUserProjectsPara) throws Exception;
	public boolean insertUserProjectTech(IstProjectTechPara insertUserProjectTechPara) throws Exception;

	public boolean updateUserTech(UdtUserTechPara updateUserTechPara) throws Exception;

	public boolean updateUserProjects(UdtProjectsPara inputUserProjectsPara) throws Exception;
	public boolean updateUserProjectTech(UdtProjectTechPara updateUserProjectTechPara) throws Exception;

	public boolean deleteUsers(InputUsersInfoPara inputUsersInfoPara) throws Exception;
	public boolean deleteUserTech(UdtUserTechPara updateUserTechPara) throws Exception;

	public boolean dltUserProjectsWithTech(DltProjectsPara dltProjectsPara) throws Exception;
	public boolean deleteUserProjectTech(DltProjectTechPara dltProjectTechPara) throws Exception;

}
