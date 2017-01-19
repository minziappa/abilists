package com.abilists.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProjectAbilistsValidator implements Validator, BaseValidator {

	final Logger logger = LoggerFactory.getLogger(ProjectAbilistsValidator.class);

	@Override
	public boolean validateSimple(String path, Configuration configuration) throws IOException {

		List<Object> pathList = configuration.getList("usertech.path");
		if(path == null || !pathList.contains(path)) {
			return false;
		}

		return true;
	}

	@Override
	public Map<String, String> validateBusiness(Errors errors, Object object, HttpSession session) throws IOException {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
