package com.abilists.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.utility.validate.ValidateUtility;

public class CharacterEscapeValidator implements ConstraintValidator<CharacterEscape, String>{

	@Override
	public void initialize(CharacterEscape constraintAnnotation) {
	}

	@Override
	public boolean isValid(String inputData, ConstraintValidatorContext ctx) {

        if(inputData == null || inputData.length() < 1){
            return false;
        }

        if(!ValidateUtility.validateSpecialCharacter(inputData)) {
        	return false;
        }

        return true;
	}

}