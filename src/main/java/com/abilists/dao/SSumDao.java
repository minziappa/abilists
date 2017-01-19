package com.abilists.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.abilists.bean.model.sum.UserProjectsSumByYearModel;

@Repository
public interface SSumDao {

	public List<UserProjectsSumByYearModel> sltUserProjectsSumByYear(Map<String, Object> map) throws SQLException;

}