package com.myclass.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class AuthenticationController {
	@GetMapping("login")
	public String authenticate(@RequestParam(value = "error", required = false) String error,
							   Model model) {
		if(error != null && !error.isEmpty()) {
			System.out.println(error);
			model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
		}
		return "admin/login";
	}
	@GetMapping("logout")
	public String logout() {
		return "admin/login";
	}
}
