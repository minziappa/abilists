package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class MIndustryModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int miNo;
	private String miLargeCategory;
	private String miMiddleCategory;
	private String miCode;
	private String miExplain;
	private String mlCode;
	private String miStatus;
	private String miDelete;
	private Date insertTime;
	private Date updateTime;

	public int getMiNo() {
		return miNo;
	}
	public void setMiNo(int miNo) {
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
