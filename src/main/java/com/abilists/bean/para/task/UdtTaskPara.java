package com.abilists.bean.para.task;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.CharacterEscape;
import com.abilists.annotation.DateFormat;
import com.abilists.common.bean.CommonPara;

public class UdtTaskPara extends CommonPara {

	private String utkNo;

    @NotNull(message = "parameter.error.null.message")
    @Size(min = 9, max = 10 ,message = "parameter.error.size.max10.message")
    @DateFormat(format = "yyyy-MM-dd", message = "parameter.error.date.format.message")
	private String utkWorkDay;

    @CharacterEscape(message = "parameter.error.escape.character.message")
    @Size(max = 150, message = "parameter.error.size.max150.message")
	private String utkTask;

	public String getUtkNo() {
		return utkNo;
	}
	public void setUtkNo(String utkNo) {
		this.utkNo = utkNo;
	}
	public String getUtkWorkDay() {
		return utkWorkDay;
	}
	public void setUtkWorkDay(String utkWorkDay) {
		this.utkWorkDay = utkWorkDay;
	}
	public String getUtkTask() {
		return utkTask;
	}
	public void setUtkTask(String utkTask) {
		this.utkTask = utkTask;
	}

}