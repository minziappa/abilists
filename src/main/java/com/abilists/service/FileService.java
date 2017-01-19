package com.abilists.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.abilists.common.bean.CommonPara;

public interface FileService extends PagingService{

	public File resizeImage(CommonPara commonPara, MultipartFile file) throws Exception;
	public String loadImage(File destFile) throws Exception;
}
