package com.abilists.bean.para.noti;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.CharacterEscape;
import com.abilists.common.bean.CommonPara;

public class UdtNotiPara extends CommonPara {

    @NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	private String notiNo;

    @NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 50 ,message = "parameter.error.size.max50.message")
	private String notiTitle;

    @CharacterEscape(message = "parameter.error.escape.character.message")
    @NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 100 ,message = "parameter.error.size.max100.message")
	private String notiContents;

	private String notiKind;

	private String notiStart;

	private String notiEnd;

	public String getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}

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
