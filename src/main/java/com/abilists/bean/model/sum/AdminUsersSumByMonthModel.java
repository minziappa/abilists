package com.abilists.bean.model.sum;

import java.io.Serializable;

public class AdminUsersSumByMonthModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cnt;
	private String month;

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

}
