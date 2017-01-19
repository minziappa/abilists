package com.abilists.bean.model.sum;

import java.io.Serializable;

public class UserProjectsSumByYearModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cnt;
	private String year;

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

}
