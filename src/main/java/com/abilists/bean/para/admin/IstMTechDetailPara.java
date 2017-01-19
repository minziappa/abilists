package com.abilists.bean.para.admin;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

public class IstMTechDetailPara  extends CommonPara {

	@NotNull(message = "mtNo")
    @Size(min = 1, max = 90 ,message = "mtNo must not exceed {max} characters")
	private String mtNo;
	@NotNull(message = "mtKind")
    @Size(min = 1, max = 90 ,message = "mtKind must not exceed {max} characters")
	private String mtKind;
	@NotNull(message = "mtdLevel")
    @Size(min = 1, max = 90 ,message = "mtdLevel must not exceed {max} characters")
	private String mtdLevel;
	@NotNull(message = "mtdLevelExplain")
    @Size(min = 1, max = 90 ,message = "mtdLevelExplain must not exceed {max} characters")
	private String mtdLevelExplain;
	@NotNull(message = "mlCode")
    @Size(min = 1, max = 90 ,message = "mlCode must not exceed {max} characters")
	private String mlCode;
	private String mtdStatus;
	private String mtdDelete;

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