package com.abilists.bean.para.account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.abilists.annotation.PwdEscape;
import com.abilists.common.bean.CommonPara;

public class UpdateEmail extends CommonPara {

    @NotNull(message = "newUserEmail.")
    @Email(message = "You should form for email.")
    @Size(min = 1, max = 45 ,message = "newUserEmail must not exceed {max} characters")
	private String newUserEmail;

    @NotNull(message = "userPwd.")
    @PwdEscape(message = "The userPwd is wrong.")
    @Size(min = 1, max = 45 ,message = "userPwd must not exceed {max} characters")
	private String userPwd;

	public String getNewUserEmail() {
		return newUserEmail;
	}

	public void setNewUserEmail(String newUserEmail) {
		this.newUserEmail = newUserEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
