package com.abilists.bean.para.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.common.bean.CommonPara;

/**
 * Action + db name + data
 */
public class SltUserNotiPara extends CommonPara {

	@NotNull(message = "notiNo")
    @Size(min = 1, max = 90 ,message = "notiNo must not exceed {max} characters")
	private String notiNo;

	public String getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}


}