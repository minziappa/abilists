package com.abilists.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.abilists.bean.para.login.CompleteConfirmPara;

@Component
public class LoginAbilistsValidator implements Validator {

	final Logger logger = LoggerFactory.getLogger(LoginAbilistsValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		if(CompleteConfirmPara.class.equals(clazz)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void validate(Object object, Errors errors) {
		if (object instanceof CompleteConfirmPara) {
			CompleteConfirmPara completeConfirmPara = (CompleteConfirmPara) object;

	        if(completeConfirmPara != null) {
	        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "","password is empty");
	        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "","password2 is empty");
	        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ntoken", "","ntoken is empty");

	    		if(!completeConfirmPara.getPassword().equals(completeConfirmPara.getPassword2())) {
					errors.rejectValue("password", "parameter.error.message");
					errors.rejectValue("password2", "parameter.error.message");
					logger.warn("Comparing password with password2 is error.");
	    		}
	        }

		}
	}

}
