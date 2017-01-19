package com.abilists.bean.para.task;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class DltTaskPara extends CommonPara {

	@NotNull(message = "parameter.error.null.message")
	@Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	private String utkNo;

	public String getUtkNo() {
		return utkNo;
	}
	public void setUtkNo(String utkNo) {
		this.utkNo = utkNo;
	}

}