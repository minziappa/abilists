package com.abilists.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmptyMaxSizeValidator implements ConstraintValidator<EmptyMaxSize, String>{

	private int max = 1;

	@Override
	public void initialize(EmptyMaxSize constraintAnnotation) {
		this.max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(String inputData, ConstraintValidatorContext ctx) {

        if(inputData == null || inputData.length() <= this.max){
            return true;
        }

        return false;
	}

}