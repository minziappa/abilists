package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class MTechModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int mtNo;
	private String mtSkill;
	private String mtKind;
	private String mtName;
	private String mtVersion;
	private String mtExplain;
	private String mtRelation;
	private String mtStatus;
	private String mtTrend;
	private String mtDelete;
	private Date insertTime;
	private Date updateTime;

	public int getMtNo() {
		return mtNo;
	}
	public void setMtNo(int mtNo) {
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
	public String getMtStatus() {
		return mtStatus;
	}
	public void setMtStatus(String mtStatus) {
		this.mtStatus = mtStatus;
	}
	public String getMtTrend() {
		return mtTrend;
	}
	public void setMtTrend(String mtTrend) {
		this.mtTrend = mtTrend;
	}
	public String getMtDelete() {
		return mtDelete;
	}
	public void setMtDelete(String mtDelete) {
		this.mtDelete = mtDelete;
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
