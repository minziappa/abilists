package com.abilists.bean.para.noti;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.DateFormat;
import com.abilists.common.bean.CommonPara;

public class IstNotiPara extends CommonPara {

    @NotNull(message = "notiTitle")
    @Size(min = 1, max = 50 ,message = "It is different String size")
	private String notiTitle;

    @NotNull(message = "notiContents")
    @Size(min = 1, max = 190 ,message = "It is different String size")
	private String notiContents;

    @NotNull(message = "notiKind")
    @Size(min = 1, max = 10 ,message = "It is different String size")
	private String notiKind;

//    @NotNull(message = "notiStart")
//    @Size(min = 9, max = 10 ,message = "It is different String size")
//    @DateFormat(format = "yyyy-MM-dd", message = "Not right format for start of date")
	private String notiStart;

//    @NotNull(message = "notiEnd")
//    @Size(min = 9, max = 10 ,message = "It is different String size")
//    @DateFormat(format = "yyyy-MM-dd", message = "Not right format for end of date")
	private String notiEnd;

	public String getNotiTitle() {
		return notiTitle;
	}

	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
	}

	public String getNotiContents() {
		return notiContents;
	}

	public void setNotiContents(String notiContents) {
		this.notiContents = notiContents;
	}

	public String getNotiKind() {
		return notiKind;
	}

	public void setNotiKind(String notiKind) {
		this.notiKind = notiKind;
	}

	public String getNotiStart() {
		return notiStart;
	}

	public void setNotiStart(String notiStart) {
		this.notiStart = notiStart;
	}

	public String getNotiEnd() {
		return notiEnd;
	}

	public void setNotiEnd(String notiEnd) {
		this.notiEnd = notiEnd;
	}

}
