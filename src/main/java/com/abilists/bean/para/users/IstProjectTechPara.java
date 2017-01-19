package com.abilists.bean.para.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.Array;
import com.abilists.common.bean.CommonPara;

/**
 * Action + db name + data
 * 
 * @author njoonk
 */
public class IstProjectTechPara extends CommonPara {

	@NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	@Array(message = "parameter.error.array.message")
    private String[] mtNo;

	@NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	private String upNo;

	private String[] uptKind;

    private String[] uptLevel;

    private String[] uptDetail;

	public String[] getMtNo() {
		return mtNo;
	}

	public void setMtNo(String[] mtNo) {
		this.mtNo = mtNo;
	}

	public String getUpNo() {
		return upNo;
	}

	public void setUpNo(String upNo) {
		this.upNo = upNo;
	}

	public String[] getUptKind() {
		return uptKind;
	}

	public void setUptKind(String[] uptKind) {
		this.uptKind = uptKind;
	}

	public String[] getUptLevel() {
		return uptLevel;
	}

	public void setUptLevel(String[] uptLevel) {
		this.uptLevel = uptLevel;
	}

	public String[] getUptDetail() {
		return uptDetail;
	}

	public void setUptDetail(String[] uptDetail) {
		this.uptDetail = uptDetail;
	}

}