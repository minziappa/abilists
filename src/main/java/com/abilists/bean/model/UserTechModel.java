package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class UserTechModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int utNo;
	private String utSkill;
	private String utKind;
	private String utLevel;
	private String utDetail;
	private String utStatus;
	private String utDelete;
	private int mtNo;
	private String userId;
	private Date insertTime;
	private Date updateTime;

	public int getUtNo() {
		return utNo;
	}
	public void setUtNo(int utNo) {
		this.utNo = utNo;
	}
	public String getUtSkill() {
		return utSkill;
	}
	public void setUtSkill(String utSkill) {
		this.utSkill = utSkill;
	}
	public String getUtKind() {
		return utKind;
	}
	public void setUtKind(String utKind) {
		this.utKind = utKind;
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
	public String getUtDelete() {
		return utDelete;
	}
	public void setUtDelete(String utDelete) {
		this.utDelete = utDelete;
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
	public int getMtNo() {
		return mtNo;
	}
	public void setMtNo(int mtNo) {
		this.mtNo = mtNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
