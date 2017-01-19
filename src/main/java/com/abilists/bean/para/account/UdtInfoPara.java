package com.abilists.bean.para.account;

import com.abilists.annotation.CharacterEscape;
import com.abilists.annotation.EmptyMaxSize;
import com.abilists.common.bean.CommonPara;

public class UdtInfoPara extends CommonPara {

    @CharacterEscape(message = "parameter.error.escape.character.message")
	@EmptyMaxSize(max=30, message = "parameter.error.size.max30.message")
	private String userName;

    private String userSex;

    private String userAges;

    @CharacterEscape(message = "parameter.error.escape.character.message")
    @EmptyMaxSize(max=150, message = "parameter.error.size.max150.message")
    private String userProfile;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserAges() {
		return userAges;
	}

	public void setUserAges(String userAges) {
		this.userAges = userAges;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

}
