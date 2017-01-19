package com.abilists.bean.para.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abilists.annotation.CharacterEscape;
import com.abilists.common.bean.CommonPara;

/**
 * Action + db name + data
 * 
 * @author njoonk
 *
 */
public class UdtProjectsPara extends CommonPara {

	@NotNull(message = "parameter.error.null.message")
    @Size(min = 1, max = 10 ,message = "parameter.error.size.max10.message")
	private String upNo;

	@CharacterEscape(message = "parameter.error.escape.character.message")
	@NotNull(message = "parameter.error.null.message")
	@Size(min = 1, max = 50, message = "parameter.error.size.max50.message")
	private String upName;

    private String upIndustrial;

    private String upExplain;

	private String upMembers;

    private String upRole;

    private String upStatus;

	public String getUpNo() {
		return upNo;
	}

	public void setUpNo(String upNo) {
		this.upNo = upNo;
	}

	public String getUpName() {
		return upName;
	}

	public void setUpName(String upName) {
		this.upName = upName;
	}

	public String getUpIndustrial() {
		return upIndustrial;
	}

	public void setUpIndustrial(String upIndustrial) {
		this.upIndustrial = upIndustrial;
	}

	public String getUpExplain() {
		return upExplain;
	}

	public void setUpExplain(String upExplain) {
		this.upExplain = upExplain;
	}

	public String getUpMembers() {
		return upMembers;
	}

	public void setUpMembers(String upMembers) {
		this.upMembers = upMembers;
	}

	public String getUpRole() {
		return upRole;
	}

	public void setUpRole(String upRole) {
		this.upRole = upRole;
	}

	public String getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(String upStatus) {
		this.upStatus = upStatus;
	}

}