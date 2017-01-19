package com.abilists.bean.model;

import java.io.Serializable;
import java.util.Date;

public class NotificationModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int notiNo;
	private String notiTitle;
	private String notiContents;
	private String notiKind;
	private Date notiStart;
	private Date notiEnd;
	private String notiStatus;
	private String notiDelete;
	private Date insertTime;
	private Date updateTime;

	public int getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(int notiNo) {
		this.notiNo = notiNo;
	}
	public String getNotiTitle() {
		return notiTitle;
	}
	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
	}
	public String getNotiContents() {
		return notiContents;
	}
	public void setNotiContents(String notiContents) {
		this.notiContents = notiContents;
	}
	public String getNotiKind() {
		return notiKind;
	}
	public void setNotiKind(String notiKind) {
		this.notiKind = notiKind;
	}
	public Date getNotiStart() {
		return notiStart;
	}
	public void setNotiStart(Date notiStart) {
		this.notiStart = notiStart;
	}
	public Date getNotiEnd() {
		return notiEnd;
	}
	public void setNotiEnd(Date notiEnd) {
		this.notiEnd = notiEnd;
	}
	public String getNotiStatus() {
		return notiStatus;
	}
	public void setNotiStatus(String notiStatus) {
		this.notiStatus = notiStatus;
	}
	public String getNotiDelete() {
		return notiDelete;
	}
	public void setNotiDelete(String notiDelete) {
		this.notiDelete = notiDelete;
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