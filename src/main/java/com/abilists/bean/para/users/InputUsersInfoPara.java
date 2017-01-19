package com.abilists.bean.para.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Action + db name + data
 * 
 * @author njoonk
 *
 */
public class InputUsersInfoPara {

	@NotNull(message = "userId")
    @Size(min = 1, max = 90 ,message = "userId must not exceed {max} characters")
	private String userId;

    @NotNull(message = "userPwd")
    @Size(min = 1, max = 150 ,message = "userPwd must not exceed {max} characters")
    private String userPwd;

    private String userCode;

    @NotNull(message = "userName")
    @Size(min = 1, max = 90 ,message = "userName must not exceed {max} characters")
	private String userName;

    private String userSex;

    private String userEmail;

    private String userProfile;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

}
