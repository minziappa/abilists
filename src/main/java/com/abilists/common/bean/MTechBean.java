package com.abilists.common.bean;

import java.util.HashMap;
import java.util.List;

import com.abilists.bean.model.MTechModel;

public class MTechBean {

	private String mtKind;
	private List<MTechModel> mtechList;

	public String getMtKind() {
		return mtKind;
	}

	public void setMtKind(String mtKind) {
		this.mtKind = mtKind;
	}

	public List<MTechModel> getMtechList() {
		return mtechList;
	}

	public void setMtechList(List<MTechModel> mtechList) {
		this.mtechList = mtechList;
	}

}
