package com.abilists.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class UploadFilesValidator implements ConstraintValidator<UploadFiles, MultipartFile>{

	final Logger logger = LoggerFactory.getLogger(UploadFilesValidator.class);

	@Override
	public void initialize(UploadFiles constraint) {
	}

	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext ctx) {

		if(multipartFile == null) {
			logger.info(" multipartFile is null.");
			return false;
		}
		if(multipartFile.isEmpty()) {
			logger.info(" multipartFile is empty.");
			return false;
		} else {
			logger.info(" multipartFile is not empty.");
		}

		return true;
	}

}