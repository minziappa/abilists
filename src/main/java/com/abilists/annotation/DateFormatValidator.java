package com.abilists.annotation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String>{

	private String formatDate = "yyyy-MM-dd";
	final Logger logger = LoggerFactory.getLogger(DateFormatValidator.class);

	@Override
	public void initialize(DateFormat constraintAnnotation) {
		this.formatDate = constraintAnnotation.format();
	}

	@Override
	public boolean isValid(String inputedDate, ConstraintValidatorContext ctx) {

        if(inputedDate == null || inputedDate.length() < 1 ){
            return false;
        }

		SimpleDateFormat sf = new SimpleDateFormat(this.formatDate);
		sf.setLenient(false);

		try {
			//if not valid, it will throw ParseException
			Date date = sf.parse(inputedDate);
			System.out.println(date);

		} catch (ParseException e) {
			logger.error("ParseException error ", e);
			return false;
		}

		return true;
	}

}