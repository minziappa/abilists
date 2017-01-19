package com.abilists.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.abilists.common.bean.CommonPara;
import com.abilists.service.AbstractService;
import com.abilists.service.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.utility.image.ImgUtility;
import io.utility.letter.DigitalUtility;

@Service
public class FileServiceImpl extends AbstractService implements FileService {

	final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	final String IMAGE_EXTENSION_NAME = "png";

	@Autowired
    private Configuration configuration;

	private File makePath(CommonPara commonPara) throws Exception {

		// Make files system
		String md5 = DigitalUtility.createDigestByIdentity(commonPara.getUserId());
		String deep1 = md5.substring(0,2);
		String targetFileName = md5.substring(2);

		StringBuffer webTargetPath = new StringBuffer();
		webTargetPath.append(configuration.getString("upload.path.img")).append(deep1)
		.append("/").append(targetFileName).append(".png");

		File file = new File(webTargetPath.toString());
		// Make directory if no file
		file.mkdirs();

		return file;
	}

	@Override
	public File resizeImage(CommonPara commonPara, MultipartFile file) throws Exception {

		// Get original image
		StringBuffer sbOrigin = new StringBuffer();
		sbOrigin.append(configuration.getString("upload.path.temp")).append(file.getOriginalFilename());
		BufferedImage originalImage = ImageIO.read(new File(sbOrigin.toString()));
		// Image type
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		// Resize image
		BufferedImage resizeImagePng = ImgUtility.resizeImage(originalImage, type);

		// Load file
		File destFile = this.makePath(commonPara);
		// Make small image
		ImageIO.write(resizeImagePng, IMAGE_EXTENSION_NAME, destFile);

		return destFile;
	}

	@Override
	public String loadImage(File destFile) throws Exception {

	    String imgAsBase64 = null;

		long fileLength = destFile.length();

		if (fileLength > Integer.MAX_VALUE) {
	        logger.error("File is too large");
	    }

		 // Create the byte array to hold the data
	    byte[] byteOut = new byte[(int)fileLength];

		try (
				FileInputStream fis = new FileInputStream(destFile);
				InputStream is = new BufferedInputStream(fis);
			){

			int n = -1;
	        while((n = is.read(byteOut)) > 0) {
	        }

			byte[] imgBytesAsBase64 = Base64.encodeBase64(byteOut);
			String imgDataAsBase64 = new String(imgBytesAsBase64);
			imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
		}

//		Map<String, String> map = new LinkedHashMap<String, String>();
//		map.put("imageData", imgAsBase64);
//
//		//Convert object to JSON string
//    	ObjectMapper mapper = new ObjectMapper();
//		String jsonInString = mapper.writeValueAsString(map);

		return imgAsBase64;
	}

}