package com.myclass.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/file")
public class ApiFileUpload {
	/*
	 * @Autowired ServletContext servletContext;
	 */

	// File với file có khác nhau không?
	@PostMapping("upload")
	public Object upload(@RequestParam MultipartFile file, HttpServletRequest req) {
		try {
			// Lấy đường dẫn tuyệt đối của thư mục chứa file upload
			String path = req.getServletContext().getRealPath("/" + "WEB-INF/assets/upload/");

			// Kiểm tra thư mục
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdir();
			}
			System.out.println(file.getOriginalFilename());
			System.out.println(file);

			File newFile = new File(path + file.getOriginalFilename());
			file.transferTo(newFile);

			return new ResponseEntity<String>("/upload/" + file.getOriginalFilename(), HttpStatus.OK);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		String path = req.getServletContext().getRealPath("/" + "WEB-INF/static/upload");
//		System.out.println(path);
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
