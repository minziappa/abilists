package com.abilists.bean.para.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class UdtMIndustryPara  extends CommonPara {

	private String miNo;
	@NotNull(message = "miLargeCategory")
    @Size(min = 1, max = 90 ,message = "miLargeCategory must not exceed {max} characters")
	private String miLargeCategory;
	private String miMiddleCategory;
	private String miCode;
	private String miExplain;
	@NotNull(message = "mlCode")
    @Size(min = 1, max = 90 ,message = "mlCode must not exceed {max} characters")
	private String mlCode;
	private String miStatus;
	private String miDelete;

	public String getMiNo() {
		return miNo;
	}
	public void setMiNo(String miNo) {
		this.miNo = miNo;
	}
	public String getMiLargeCategory() {
		return miLargeCategory;
	}
	public void setMiLargeCategory(String miLargeCategory) {
		this.miLargeCategory = miLargeCategory;
	}
	public String getMiMiddleCategory() {
		return miMiddleCategory;
	}
	public void setMiMiddleCategory(String miMiddleCategory) {
		this.miMiddleCategory = miMiddleCategory;
	}
	public String getMiCode() {
		return miCode;
	}
	public void setMiCode(String miCode) {
		this.miCode = miCode;
	}
	public String getMiExplain() {
		return miExplain;
	}
	public void setMiExplain(String miExplain) {
		this.miExplain = miExplain;
	}
	public String getMlCode() {
		return mlCode;
	}
	public void setMlCode(String mlCode) {
		this.mlCode = mlCode;
	}
	public String getMiStatus() {
		return miStatus;
	}
	public void setMiStatus(String miStatus) {
		this.miStatus = miStatus;
	}
	public String getMiDelete() {
		return miDelete;
	}
	public void setMiDelete(String miDelete) {
		this.miDelete = miDelete;
	}

}