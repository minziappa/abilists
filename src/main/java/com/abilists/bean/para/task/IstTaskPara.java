package com.abilists.bean.para.task;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.CharacterEscape;
import com.abilists.annotation.DateFormat;
import com.abilists.common.bean.CommonPara;

public class IstTaskPara extends CommonPara {

    @NotNull(message = "utkWorkDay")
    @Size(min = 9, max = 10 ,message = "It is different String size")
    @DateFormat(format = "yyyy-MM-dd", message = "Not right format for start of date")
	private String utkWorkDay;

    @CharacterEscape(message = "parameter.error.escape.character.message")
    @Size(max = 150, message = "parameter.error.size.max150.message")
	private String utkTask;

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