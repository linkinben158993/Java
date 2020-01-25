package com.myclass.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController {
	@GetMapping("403")
	public String notFound() {
		return "error/403";
	}
}
