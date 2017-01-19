package com.abilists.bean.para.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

/**
 * Action + db name + data
 * 
 * @author njoonk
 */
public class UdtProjectTechPara extends CommonPara {

	@NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
    private String mtNo;

	private String upNo;

	@NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	private String uptNo;
	
	private String uptKind;

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

	public String getUptNo() {
		return uptNo;
	}

	public void setUptNo(String uptNo) {
		this.uptNo = uptNo;
	}

	public String getUptKind() {
		return uptKind;
	}

	public void setUptKind(String uptKind) {
		this.uptKind = uptKind;
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