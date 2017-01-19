package com.abilists.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.utility.validate.ValidateUtility;

public class StringEscapeValidator implements ConstraintValidator<StringEscape, String>{

	@Override
	public void initialize(StringEscape constraintAnnotation) {
	}

	@Override
	public boolean isValid(String inputData, ConstraintValidatorContext ctx) {

        if(inputData == null || inputData.length() < 1){
            return false;
        }

        if(!ValidateUtility.validateCharacters(inputData)) {
        	return false;
        }

        return true;
	}

}