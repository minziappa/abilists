package com.abilists.bean.para.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

/**
 * Action + db name + data
 * 
 * @author njoonk
 */
public class InputUserProjectTechPara extends CommonPara {

	@NotNull(message = "mtNo")
    @Size(min = 1, max = 90 ,message = "mtNo must not exceed {max} characters")
    private String mtNo;

	@NotNull(message = "upNo")
    @Size(min = 1, max = 90 ,message = "upNo must not exceed {max} characters")
	private String upNo;

    private String uptLevel;

    private String uptDetail;

	private String uptStatus;

	public String getMtNo() {
		return mtNo;
	}

	public void setMtNo(String mtNo) {
		this.mtNo = mtNo;
	}

	public String getUpNo() {
		return upNo;
	}

	public void setUpNo(String upNo) {
		this.upNo = upNo;
	}

	public String getUptLevel() {
		return uptLevel;
	}

	public void setUptLevel(String uptLevel) {
		this.uptLevel = uptLevel;
	}

	public String getUptDetail() {
		return uptDetail;
	}

	public void setUptDetail(String uptDetail) {
		this.uptDetail = uptDetail;
	}

	public String getUptStatus() {
		return uptStatus;
	}

	public void setUptStatus(String uptStatus) {
		this.uptStatus = uptStatus;
	}

}