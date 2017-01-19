package com.abilists.bean.para;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class LoginPara extends CommonPara {

    @NotNull(message = "parameter.login.id.pwd.error.message")
    @Size(min = 1, max = 50 ,message = "parameter.error.size.max50.message")
	private String userId;

    @NotNull(message = "parameter.login.id.pwd.error.message")
    @Size(min = 1, max = 100 ,message = "parameter.error.size.max100.message")
	private String userPwd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
