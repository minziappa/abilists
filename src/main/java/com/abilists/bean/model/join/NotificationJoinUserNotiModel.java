package com.abilists.bean.model.join;

import java.io.Serializable;
import java.util.Date;

public class NotificationJoinUserNotiModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int notiNo;
	private String notiTitle;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}