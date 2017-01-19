package com.abilists.bean.para.noti;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class DltNotiPara extends CommonPara {

	@NotNull(message = "parameter.error.null.message")
	@Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	private String notiNo;

	public String getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}

}
