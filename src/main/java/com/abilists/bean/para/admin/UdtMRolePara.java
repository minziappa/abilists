package com.abilists.bean.para.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class UdtMRolePara  extends CommonPara {

	@NotNull(message = "mrNo")
    @Size(min = 1, max = 90 ,message = "mrNo must not exceed {max} characters")
	private String mrNo;
	@NotNull(message = "mrName")
    @Size(min = 1, max = 90 ,message = "mrName must not exceed {max} characters")
	private String mrName;
	private String mrCode;
	private String mrExplain;
	private String mrResponsibilities;
	private String mrWorks;
	private String mrSkills;
	private String mrPrefers;
	@NotNull(message = "mlCode")
    @Size(min = 1, max = 90 ,message = "mlCode must not exceed {max} characters")
	private String mlCode;
	private String mrStatus;
	private String mrDelete;

	public String getMrNo() {
		return mrNo;
	}
	public void setMrNo(String mrNo) {
		this.mrNo = mrNo;
	}
	public String getMrName() {
		return mrName;
	}
	public void setMrName(String mrName) {
		this.mrName = mrName;
	}
	public String getMrCode() {
		return mrCode;
	}
	public void setMrCode(String mrCode) {
		this.mrCode = mrCode;
	}
	public String getMrExplain() {
		return mrExplain;
	}
	public void setMrExplain(String mrExplain) {
		this.mrExplain = mrExplain;
	}
	public String getMrResponsibilities() {
		return mrResponsibilities;
	}
	public void setMrResponsibilities(String mrResponsibilities) {
		this.mrResponsibilities = mrResponsibilities;
	}
	public String getMrWorks() {
		return mrWorks;
	}
	public void setMrWorks(String mrWorks) {
		this.mrWorks = mrWorks;
	}
	public String getMrSkills() {
		return mrSkills;
	}
	public void setMrSkills(String mrSkills) {
		this.mrSkills = mrSkills;
	}
	public String getMrPrefers() {
		return mrPrefers;
	}
	public void setMrPrefers(String mrPrefers) {
		this.mrPrefers = mrPrefers;
	}
	public String getMlCode() {
		return mlCode;
	}
	public void setMlCode(String mlCode) {
		this.mlCode = mlCode;
	}
	public String getMrStatus() {
		return mrStatus;
	}
	public void setMrStatus(String mrStatus) {
		this.mrStatus = mrStatus;
	}
	public String getMrDelete() {
		return mrDelete;
	}
	public void setMrDelete(String mrDelete) {
		this.mrDelete = mrDelete;
	}

}