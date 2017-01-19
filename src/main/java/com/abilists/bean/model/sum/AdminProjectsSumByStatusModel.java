package com.abilists.bean.model.sum;

import java.io.Serializable;

public class AdminProjectsSumByStatusModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cnt;
	private String up_status;

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getUp_status() {
		return up_status;
	}
	public void setUp_status(String up_status) {
		this.up_status = up_status;
	}

}
