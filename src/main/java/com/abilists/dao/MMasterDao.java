package com.abilists.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MMasterDao {

	public int insertMRole(Map<String, Object> map) throws SQLException;
	public int insertMTech(Map<String, Object> map) throws SQLException;
	public int insertMTechDetail(Map<String, Object> map) throws SQLException;
	public int insertMIndustry(Map<String, Object> map) throws SQLException;

	public int updateMRole(Map<String, Object> map) throws SQLException;
	public int updateMTech(Map<String, Object> map) throws SQLException;
	public int updateMTechDetail(Map<String, Object> map) throws SQLException;
	public int updateMIndustry(Map<String, Object> map) throws SQLException;

	public int deleteMRole(Map<String, Object> map) throws SQLException;
	public int deleteMTech(Map<String, Object> map) throws SQLException;
	public int deleteMTechDetail(Map<String, Object> map) throws SQLException;
	public int deleteMIndustry(Map<String, Object> map) throws SQLException;

}