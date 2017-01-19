package com.abilists.common.bean;

import io.paging.bean.PagingBean;

public class CommonModel {

	private String navi;
	private String menu;
	private String sidebar;
	private String lang;
	private String token;
    private PagingBean paging;
    private String errorMessage;

	public String getNavi() {
		return navi;
	}
	public void setNavi(String navi) {
		this.navi = navi;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getSidebar() {
		return sidebar;
	}
	public void setSidebar(String sidebar) {
		this.sidebar = sidebar;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public PagingBean getPaging() {
		return paging;
	}
	public void setPaging(PagingBean paging) {
		this.paging = paging;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
