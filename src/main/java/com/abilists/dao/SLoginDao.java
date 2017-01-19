package com.abilists.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.abilists.bean.model.UserTemp;

@Repository
public interface SLoginDao {
	
	public UserTemp sltUserTemp(Map<String, Object> map) throws SQLException;
	public String sltUserPwd(Map<String, Object> map) throws SQLException;
	public int sltUserCnt(Map<String, Object> map) throws SQLException;

}