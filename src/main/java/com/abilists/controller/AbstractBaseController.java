package com.abilists.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abilists.bean.KeyTokenBean;
import com.abilists.bean.model.UsersModel;
import com.abilists.common.bean.CommonPara;

import io.utility.letter.DigitalUtility;
import io.utility.security.TokenUtility;

public abstract class AbstractBaseController {

	public final Locale LOCALE = Locale.ENGLISH;
	final Logger logger = LoggerFactory.getLogger(AbstractBaseController.class);

	@Autowired
	public MessageSource message;

	private String getFileName(String fileName, String extend) {

		StringBuffer sb = new StringBuffer();
		String [] arrayFileName = fileName.split("\\.");
		if(arrayFileName.length < 2) {
			sb.append(fileName).append(".").append(extend);
			fileName = sb.toString();
		}

		return fileName;
	}

	/*
	 * Generate key and token for user without account.
	 */
	public KeyTokenBean generateKeyAndToken() throws Exception {

		KeyTokenBean keyToken = new KeyTokenBean();

		String key = UUID.randomUUID().toString();
		key = key.replaceAll("-", "");
		keyToken.setKey(key);
		keyToken.setToken(TokenUtility.generateToken(TokenUtility.SHA_256));

		return keyToken;
	}

	public void handleWriteAjax(String jsonString, HttpServletResponse response) throws IOException {

		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();

		pw.write(jsonString);
		pw.flush();
		pw.close();
	}

	public void handleWriteImage(BufferedImage bufferedImage, HttpServletResponse response) throws IOException {

		response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(bufferedImage, "png", os);

        os.flush();
        os.close();
	}

	public String handleReadImage(String userId, String uploadPath) throws Exception {

		// Parsing userId to path
		String md5 = DigitalUtility.createDigestByIdentity(userId);
		String deep1 = md5.substring(0,2);
		String targetFileName = md5.substring(2);

		// Set path
		StringBuffer webTargetPath = new StringBuffer();
		webTargetPath.append(uploadPath).append(deep1).append("/").append(targetFileName).append(".png");

		File file = new File(webTargetPath.toString());
		if(!file.exists()) {
			logger.warn("There is no your image. It is changed to the default image.");
			file = new File(uploadPath + "default.png");
			if(!file.exists()) {
				logger.error("No default image in server. please check the default image.");
				return "";
			}
		}

		// Set length of file
		long fileLength = file.length();

		// Create the byte array to hold the data
	    byte[] byteOut = new byte[(int)fileLength];

	    InputStream is = new BufferedInputStream(new FileInputStream(file));
        int n = -1;
        while((n = is.read(byteOut)) > 0) {}

		byte[] imgBytesAsBase64 = Base64.encodeBase64(byteOut);
		String imgDataAsBase64 = new String(imgBytesAsBase64);
		String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;

        is.close();

        return imgAsBase64;
	}

	public void handleFileDownload(String fileName, String extend, byte[] byteOut, HttpServletResponse response) throws IOException {
		fileName = this.getFileName(fileName, extend);
		this.handleFileDownload(fileName, byteOut, response);
	}

	public void handleFileDownload(String fullFileName, byte[] byteOut, HttpServletResponse response) throws IOException {
		response.setHeader("Content-Length", String.valueOf(byteOut.length));
		response.setHeader("Content-Disposition", "attachment;filename=" + fullFileName);
		response.setContentType("application/octet-stream");
        OutputStream os = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(byteOut);
        int n = -1;
        while((n = in.read(byteOut)) > 0) {
        	os.write(byteOut, 0, n);
        }
        in.close();
        os.flush();
        os.close();
	}

	public void handleSaveFile(String fileName, String extend, byte[] byteOut, String path) throws IOException {
		String fullFileName = this.getFileName(fileName, extend);
		handleSaveFile(fullFileName, byteOut, path);
	}

	public void handleSaveFile(String fullFileName, byte[] byteOut, String path) throws IOException {

		File file = new File(path + fullFileName);
		logger.info("saving path = " + file.getPath());

		OutputStream os = null;
		InputStream  in = null;
		try {
			os = new FileOutputStream(file);
	        in = new ByteArrayInputStream(byteOut);
	        int n = -1;
	        while((n = in.read(byteOut)) > 0) {
	        	os.write(byteOut, 0, n);
	        }
		} catch (IOException e) {
			logger.error("You have to make the path likt this=" + file.getPath(), e);
			throw e;
		} finally {
	        in.close();
	        os.flush();
	        os.close();			
		}

	}

	public void handleValidator(List<ObjectError> errorList) throws IOException {

        for(int i=0; i< errorList.size();i++) {
            ObjectError error = errorList.get(i);
            logger.error("DefaultMessage = " + error.getDefaultMessage());
            logger.error("ObjectName = " + error.getObjectName());

            Object[] codes = error.getCodes();
            for(int m=0; m < codes.length; m++) {
            	String str = (String) codes[m];
            	logger.error("codes = " + str);
            }
        }
	}

	public Map<String, String> handleErrorMessages(List<ObjectError> errorList) throws IOException {

		Map<String, String> mapErrorMsg = new HashMap<String, String>();

		// This is default value
		for (Object objectError : errorList) {
		    FieldError fieldError = (FieldError) objectError;

            mapErrorMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
			logger.error("field name = " + fieldError.getField());
			logger.error("Object name = " + fieldError.getRejectedValue());
            logger.error("error message = " + fieldError.getDefaultMessage());
		}

		return mapErrorMsg;
	}

	public Map<String, String> handleErrorMessages(List<ObjectError> errorList, Locale locale) throws IOException {

		Map<String, String> mapErrorMsg = new HashMap<String, String>();

		// This is default value
		String errorMessage = "parameter.error.invalid";
		for (Object objectError : errorList) {
		    FieldError fieldError = (FieldError) objectError;
			logger.error("field name = " + fieldError.getField());
		    try {
			    // Set messages into map to show pages
			    errorMessage = message.getMessage(fieldError.getDefaultMessage(), null, locale);
	            mapErrorMsg.put(fieldError.getField(), errorMessage);
			} catch (NoSuchMessageException e) {
				logger.error("There is no message key. field name = " + fieldError.getField());
			}

            logger.error("error message in detail = " + errorMessage);
		}

		return mapErrorMsg;
	}

	public boolean handleIphone(HttpSession session) throws Exception {

		Boolean blnIphone = (Boolean)session.getAttribute("blnIphone");

		return blnIphone;
	}

	public CommonPara handleSessionInfo(HttpSession session, CommonPara commonPara) throws Exception {

		// Get user id
		UsersModel user = (UsersModel) session.getAttribute("user");
		commonPara.setUserId(user.getUserId());
		String lang = (String) session.getAttribute("lang");
		commonPara.setLang(lang);

		return commonPara;
	}

	public String handleLang(HttpSession session) throws Exception {
		return (String) session.getAttribute("lang");
	}

	public CommonPara handleLang(HttpSession session, CommonPara commonPara) throws Exception {

		// Get lang
		String lang = (String) session.getAttribute("lang");
		commonPara.setLang(lang);

		return commonPara;
	}

	@ExceptionHandler(Exception.class)
	public void handleException(Exception e, HttpServletResponse response, 
			HttpServletRequest request) throws IOException {

		Enumeration<?> en = (Enumeration<?>) request.getParameterNames();

		int i = 0;
        while(en.hasMoreElements()) {
        	i++;
            String name = (String)en.nextElement();
            String value = request.getParameter(name);
            logger.error("Exception:" + i + " parameter is name=" + name + ", value=" + value);
        }

        logger.error("Exception's trace:", e);

		response.setContentLength(0);
		response.setStatus(500);
	}

	@ExceptionHandler(SQLException.class)
	public void handleException(SQLException e, HttpServletResponse response, 
			HttpServletRequest request) throws IOException {

		Enumeration<?> en = (Enumeration<?>) request.getParameterNames();

		int i = 0;
        while(en.hasMoreElements()) {
        	i++;
            String name = (String)en.nextElement();
            String value = request.getParameter(name);
            logger.error("SQLException:" + i + " parameter is name=" + name + ", value=" + value);
        }

		response.setContentLength(0);
		response.setStatus(500);
	}

}