package com.abilists.service;

import com.abilists.bean.model.UsersModel;
import com.abilists.bean.para.account.UpdateEmail;
import com.abilists.bean.para.account.UdtInfoPara;
import com.abilists.bean.para.account.UdtPwd;
import com.abilists.common.bean.CommonPara;

public interface AccountService {

	public boolean updatedPwd(UdtPwd updatePassword) throws Exception;
	public boolean updatedEmail(UpdateEmail updateEmail) throws Exception;

	public UsersModel sltInfo(CommonPara commonPara) throws Exception;
	public boolean updateInfo(UdtInfoPara updateInfoPara) throws Exception;

}