package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class MTechDetailModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int mtdNo;
	private int mtNo;
	private String mtKind;
	private int mtdLevel;
	private String mtdLevelExplain;
	private String mlCode;
	private String mtdStatus;
	private String mtdDelete;
	private Date insertTime;
	private Date updateTime;

	public int getMtdNo() {
		return mtdNo;
	}
	public void setMtdNo(int mtdNo) {
		this.mtdNo = mtdNo;
	}
	public int getMtNo() {
		return mtNo;
	}
	public void setMtNo(int mtNo) {
		this.mtNo = mtNo;
	}
	public String getMtKind() {
		return mtKind;
	}
	public void setMtKind(String mtKind) {
		this.mtKind = mtKind;
	}
	public int getMtdLevel() {
		return mtdLevel;
	}
	public void setMtdLevel(int mtdLevel) {
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
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
