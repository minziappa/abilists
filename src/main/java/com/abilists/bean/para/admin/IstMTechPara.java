package com.abilists.bean.para.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class IstMTechPara  extends CommonPara {

	private String mtNo;
	@NotNull(message = "mtSkill")
    @Size(min = 1, max = 90 ,message = "mtSkill must not exceed {max} characters")
	private String mtSkill;

	@NotNull(message = "mtKind")
    @Size(min = 1, max = 90 ,message = "mtKind must not exceed {max} characters")
	private String mtKind;
	private String mtName;
	private String mtVersion;
	private String mtExplain;
	private String mtRelation;
	private String mtTrend;
	private String mtStatus;
	private String mtDelete;

	public String getMtNo() {
		return mtNo;
	}
	public void setMtNo(String mtNo) {
		this.mtNo = mtNo;
	}
	public String getMtSkill() {
		return mtSkill;
	}
	public void setMtSkill(String mtSkill) {
		this.mtSkill = mtSkill;
	}
	public String getMtKind() {
		return mtKind;
	}
	public void setMtKind(String mtKind) {
		this.mtKind = mtKind;
	}
	public String getMtName() {
		return mtName;
	}
	public void setMtName(String mtName) {
		this.mtName = mtName;
	}
	public String getMtVersion() {
		return mtVersion;
	}
	public void setMtVersion(String mtVersion) {
		this.mtVersion = mtVersion;
	}
	public String getMtExplain() {
		return mtExplain;
	}
	public void setMtExplain(String mtExplain) {
		this.mtExplain = mtExplain;
	}
	public String getMtRelation() {
		return mtRelation;
	}
	public void setMtRelation(String mtRelation) {
		this.mtRelation = mtRelation;
	}
	public String getMtTrend() {
		return mtTrend;
	}
	public void setMtTrend(String mtTrend) {
		this.mtTrend = mtTrend;
	}
	public String getMtStatus() {
		return mtStatus;
	}
	public void setMtStatus(String mtStatus) {
		this.mtStatus = mtStatus;
	}
	public String getMtDelete() {
		return mtDelete;
	}
	public void setMtDelete(String mtDelete) {
		this.mtDelete = mtDelete;
	}

	
	
}