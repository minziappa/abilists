package com.abilists.bean.para.account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.PwdEscape;
import com.abilists.common.bean.CommonPara;

public class UdtPwd extends CommonPara {

    @NotNull(message = "parameter.error.null.message")
    @Size(min = 7, max = 100 ,message = "parameter.error.size.min8.max100.current.password.message")
	private String currentPwd;

    @NotNull(message = "parameter.error.null.message")
    @PwdEscape(message = "parameter.error.escape.password.message")
    @Size(min = 10, max = 100 ,message = "parameter.error.size.min10.max100.new.password.message")
	private String password;

    @NotNull(message = "parameter.error.null.message")
    @PwdEscape(message = "parameter.error.escape.password.message")
    @Size(min = 10, max = 100 ,message = "parameter.error.size.min10.max100.new.password.message")
	private String password2;

	public String getCurrentPwd() {
		return currentPwd;
	}

	public void setCurrentPwd(String currentPwd) {
		this.currentPwd = currentPwd;
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

}
