package com.myclass.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myclass.service.GenericService;

@Controller
public abstract class GenericController<T> {
	@Autowired
	GenericService<T, String> genericService;
	
	private String page;
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public GenericController(String page) {
		this.page = page;
	}
	
	@GetMapping("")
	public String index(Model model, String page) {
		model.addAttribute("roles", genericService.findAll());

		return "/" + page + "/index";
	}
}
