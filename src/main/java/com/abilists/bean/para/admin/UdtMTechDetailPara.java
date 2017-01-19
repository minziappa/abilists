package com.abilists.bean.para.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.CharacterEscape;
import com.abilists.common.bean.CommonPara;

public class UdtMTechDetailPara  extends CommonPara {

	@NotNull(message = "parameter.error.null.message")
	@Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	private String mtdNo;

	private String mtNo;

	private String mtKind;

	@NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 90 ,message = "mtdLevel must not exceed {max} characters")
	private String mtdLevel;

	@NotNull(message = "parameter.error.null.message")
	@Size(max = 15, message = "parameter.error.size.max15.message")
	@CharacterEscape(message = "parameter.error.escape.character.message")
	private String mtdLevelExplain;

	@NotNull(message = "parameter.error.null.message")
	@Size(max = 10, message = "parameter.error.size.max10.message")
	private String mlCode;
	private String mtdStatus;
	private String mtdDelete;

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
	public String getMtKind() {
		return mtKind;
	}
	public void setMtKind(String mtKind) {
		this.mtKind = mtKind;
	}
	public String getMtdLevel() {
		return mtdLevel;
	}
	public void setMtdLevel(String mtdLevel) {
		this.mtdLevel = mtdLevel;
	}
	public String getMtdLevelExplain() {
		return mtdLevelExplain;
	}
	public void setMtdLevelExplain(String mtdLevelExplain) {
		this.mtdLevelExplain = mtdLevelExplain;
	}
	public String getMlCode() {
		return mlCode;
	}
	public void setMlCode(String mlCode) {
		this.mlCode = mlCode;
	}
	public String getMtdStatus() {
		return mtdStatus;
	}
	public void setMtdStatus(String mtdStatus) {
		this.mtdStatus = mtdStatus;
	}
	public String getMtdDelete() {
		return mtdDelete;
	}
	public void setMtdDelete(String mtdDelete) {
		this.mtdDelete = mtdDelete;
	}

}