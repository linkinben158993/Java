package com.myclass.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Courses;
import com.myclass.entity.Users;
import com.myclass.service.GenericService;
import com.myclass.service.UserService;

@Controller
@RequestMapping("courses")
public class CourseController {
	@Autowired
	GenericService<Users, String> genericService;
	@Autowired
	UserService userService;
	private List<Courses> courses;
	
	public CourseController() {
		courses = new ArrayList<Courses>();
	}
	
	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("courses", courses);
		return "courses/index";
	}
	
	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("course", new Courses());
		return "/courses/add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("course") Courses course, BindingResult errors) {
		
		if(errors.hasErrors()) {
			return "/courses/add";
		}
		
		courses.add(course);
		
		return "redirect:/courses";
	}
}
