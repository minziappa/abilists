package com.abilists.bean;

import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

public class SlaveDbMappingBean {

	private Map<String, BasicDataSource> sltedSlaveDb;

	public Map<String, BasicDataSource> getSltedSlaveDb() {
		return sltedSlaveDb;
	}

	public void setSltedSlaveDb(Map<String, BasicDataSource> sltedSlaveDb) {
		this.sltedSlaveDb = sltedSlaveDb;
	}

}