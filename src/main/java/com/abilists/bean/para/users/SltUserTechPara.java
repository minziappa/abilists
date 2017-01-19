package com.abilists.bean.para.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

/**
 * Action + db name + data
 */
public class SltUserTechPara extends CommonPara {

	@NotNull(message = "mtNo")
    @Size(min = 1, max = 90 ,message = "mtNo must not exceed {max} characters")
    private String mtNo;

    private String utSkill;

    private String utLevel;

    private String utDetail;

	private String utStatus;

	public String getMtNo() {
		return mtNo;
	}

	public void setMtNo(String mtNo) {
		this.mtNo = mtNo;
	}

	public String getUtSkill() {
		return utSkill;
	}

	public void setUtSkill(String utSkill) {
		this.utSkill = utSkill;
	}

	public String getUtLevel() {
		return utLevel;
	}

	public void setUtLevel(String utLevel) {
		this.utLevel = utLevel;
	}

	public String getUtDetail() {
		return utDetail;
	}

	public void setUtDetail(String utDetail) {
		this.utDetail = utDetail;
	}

	public String getUtStatus() {
		return utStatus;
	}

	public void setUtStatus(String utStatus) {
		this.utStatus = utStatus;
	}

}