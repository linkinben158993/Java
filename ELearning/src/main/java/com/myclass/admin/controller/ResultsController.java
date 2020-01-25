package com.myclass.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Results;

@Controller
@RequestMapping("targets")
public class ResultsController {
	private List<Results> results;
	
	public ResultsController() {
		results = new ArrayList<Results>();
	}
	
	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("results",results);
		return "/targets/index";
	}
}
