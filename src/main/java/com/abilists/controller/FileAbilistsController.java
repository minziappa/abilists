package com.abilists.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.abilists.bean.AbilistsModel;
import com.abilists.bean.para.users.UploadUserFilePara;
import com.abilists.service.FileService;

// @SessionAttributes("userPicture")
@Controller
@RequestMapping("/file")
public class FileAbilistsController extends AbstractBaseController {

	final Logger logger = LoggerFactory.getLogger(FileAbilistsController.class);

	@Autowired
    private Configuration configuration;
	@Autowired
	private FileService fileService;

	@RequestMapping(value = {"uploadUserFile"})
	public String uploadUserFile(@Validated UploadUserFilePara uploadUserFilePara, 
			BindingResult bindingResult, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AbilistsModel abilistsModel = new AbilistsModel();
		abilistsModel.setNavi("account");
		abilistsModel.setMenu("settings");
		abilistsModel.setSidebar("introPicture");

		Map<String, String> mapErrorMessage = null;
		// If it occurs a error, set the default value.
		if (bindingResult.hasErrors()) {
			logger.error("uploadUserFile - it is occured a parameter error.");
			response.setStatus(400);
			Locale locale = RequestContextUtils.getLocale(request);
			mapErrorMessage = this.handleErrorMessages(bindingResult.getAllErrors(), locale);
			model.addAttribute("mapErrorMessage",  mapErrorMessage);
			return "account/introPicture";
		}

		this.handleSessionInfo(request.getSession(), uploadUserFilePara);

		MultipartFile multipartFile = uploadUserFilePara.getUserImg();

		// Save the original image
		this.handleSaveFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(), 
				configuration.getString("upload.path.temp"));
		// Make a small image
		fileService.resizeImage(uploadUserFilePara, multipartFile);

		return "redirect:/account/introPicture";
	}

//	/*
//	 * Not used, but save for backup
//	 * Used to upload user picture.
//	 */
//	@RequestMapping(value = {"uploadUserFileAjax"})
//	public void uploadUserFileAjax(@RequestParam("userImg") Object userImg, 
//			HttpSession session, HttpServletResponse response) throws Exception {
//
//		AbilistsModel abilistsModel = new AbilistsModel();
//		abilistsModel.setNavi("account");
//		abilistsModel.setMenu("settings");
//		abilistsModel.setSidebar("introPicture");
//
//		// Set user id
//		CommonPara commonPara = new CommonPara();
//		this.handleSessionInfo(session, commonPara);
//
//		MultipartFile multipartFile = (MultipartFile) userImg;
//
//		if(multipartFile == null) {
//			this.handleWriteAjax("error", response);
//			return;
//		}
//
//		// Save image
//		this.handleSaveFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(), 
//				configuration.getString("upload.path.temp"));
//
//		File file = fileService.resizeImage(commonPara, multipartFile);
//		String imgAsBase64 = fileService.loadImage(file);
//
//		// Set user image into session
//		session.setAttribute("userPicture", imgAsBase64);
//
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		map.put("imageData", imgAsBase64);
//
//		//Convert object to JSON string
//    	ObjectMapper mapper = new ObjectMapper();
//		String jsonInString = mapper.writeValueAsString(map);
//
//		logger.info("jsonInString >> " + jsonInString);
//
//		this.handleWriteAjax(jsonInString, response);
//	}

// For test
//	@RequestMapping(value = {"image/{path}/{imgName}"})
//	public String image(@PathVariable String path, @PathVariable String imgName, 
//			ModelMap model, HttpServletResponse response) throws Exception {
//
//		StringBuffer sbImageFile = new StringBuffer();
//		sbImageFile.append(configuration.getString("upload.path")).append(path)
//			.append("/").append(imgName).append(".png");
//		logger.info("file path >> " + sbImageFile.toString());
//
//		File file = new File(sbImageFile.toString());
//		long fileLength = file.length();
//
//		if (fileLength > Integer.MAX_VALUE) {
//	        logger.error("File is too large");
//	    }
//
//		 // Create the byte array to hold the data
//	    byte[] byteOut = new byte[(int)fileLength];
//
//	    InputStream is = new BufferedInputStream(new FileInputStream(file));
//
//        int n = -1;
//        while((n = is.read(byteOut)) > 0) {
//        }
//
//		byte[] imgBytesAsBase64 = Base64.encodeBase64(byteOut);
//		String imgDataAsBase64 = new String(imgBytesAsBase64);
//		String imgAsBase64 = "data:image/png;base64," + imgDataAsBase64;
//
//        is.close();
//
//        model.addAttribute("test", imgAsBase64);
//        return "files/test";
//	}

}