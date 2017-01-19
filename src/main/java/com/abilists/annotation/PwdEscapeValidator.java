package com.abilists.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.utility.validate.ValidateUtility;

public class PwdEscapeValidator implements ConstraintValidator<PwdEscape, String>{

	@Override
	public void initialize(PwdEscape constraintAnnotation) {
	}

	@Override
	public boolean isValid(String pwdData, ConstraintValidatorContext ctx) {

        if(pwdData == null || pwdData.length() < 1){
            return false;
        }

        if(!ValidateUtility.validatePassword(pwdData)) {
        	return false;
        }

        return true;
	}

}