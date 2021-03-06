package com.abilists.bean.para.login;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.PwdEscape;
import com.abilists.common.bean.CommonPara;

public class CompleteConfirmPara extends CommonPara {

    @NotNull(message = "userId.")
    @Size(min = 1, max = 45 ,message = "userId must not exceed {max} characters")
	private String userId;

    @NotNull(message = "password.")
    @PwdEscape(message = "There is not good password1.")
    @Size(min = 1, max = 45 ,message = "password must not exceed {max} characters")
	private String password;

    @NotNull(message = "password2.")
    @PwdEscape(message = "There is not good password2.")
    @Size(min = 1, max = 45 ,message = "password2 must not exceed {max} characters")
	private String password2;

    @NotNull(message = "ntoken")
    @Size(min = 1, max = 150 ,message = "nToken must not exceed {max} characters")
	private String ntoken;
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getNtoken() {
		return ntoken;
	}

	public void setNtoken(String ntoken) {
		this.ntoken = ntoken;
	}

}