package com.abilists.bean.para.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class DltMIndustryPara extends CommonPara {

    @NotNull(message = "miNo")
    @Size(min = 1, max = 10 ,message = "miNo must not exceed {max} characters")
    private String miNo;

	public String getMiNo() {
		return miNo;
	}

	public void setMiNo(String miNo) {
		this.miNo = miNo;
	}

}
