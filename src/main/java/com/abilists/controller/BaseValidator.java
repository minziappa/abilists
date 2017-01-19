package com.abilists.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.springframework.validation.Errors;

public interface BaseValidator {

	final Locale LOCALE = Locale.JAPAN;

	public Map<String, String> validateBusiness(Errors errors, Object object, HttpSession session) throws IOException;
	public boolean validateSimple(String path, Configuration configuration) throws IOException;

}
