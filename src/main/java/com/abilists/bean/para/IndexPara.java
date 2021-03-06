package com.abilists.bean.para;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IndexPara {

    @NotNull(message = "userId")
    @Size(min = 1, max = 45 ,message = "userId")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
