package com.abilists.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MSequenceDao {
	public long updateSequece(Map<String, Object> map) throws SQLException;
}