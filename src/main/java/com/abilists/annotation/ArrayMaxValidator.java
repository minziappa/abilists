package com.abilists.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArrayMaxValidator implements ConstraintValidator<ArrayMax, String[]>{

	private int max = 1;

	@Override
	public void initialize(ArrayMax constraintAnnotation) {
		this.max = constraintAnnotation.max();
	}

	@Override
	public boolean isValid(String[] array, ConstraintValidatorContext ctx) {
		for(String para : array) {
			if(para == null) return false;
			else if(para.trim().length() > this.max) return false;
		}
		return true;

	}

}