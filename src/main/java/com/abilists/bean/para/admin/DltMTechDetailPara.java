package com.abilists.bean.para.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class DltMTechDetailPara extends CommonPara {

	@NotNull(message = "parameter.error.null.message")
	@Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	private String mtdNo;

	@NotNull(message = "parameter.error.null.message")
	@Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
    private String mtNo;

	public String getMtdNo() {
		return mtdNo;
	}

	public void setMtdNo(String mtdNo) {
		this.mtdNo = mtdNo;
	}

	public String getMtNo() {
		return mtNo;
	}

	public void setMtNo(String mtNo) {
		this.mtNo = mtNo;
	}

}
