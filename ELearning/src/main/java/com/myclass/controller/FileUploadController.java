package com.myclass.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {
	@Autowired
	ServletContext servletContext;

	@PostMapping(value = "upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Object upload(@RequestParam() MultipartFile file) {
		String pathName = servletContext.getRealPath("/WEB-INF/assets/templates/upload/");
		File dir = new File(pathName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String pathSource = pathName + file.getOriginalFilename();
		File serverFile = new File(pathSource);
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(serverFile);
			stream.write(file.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<String, String> result = new HashMap<String, String>();
		result.put("url", file.getOriginalFilename());
		return result;
	}
}
