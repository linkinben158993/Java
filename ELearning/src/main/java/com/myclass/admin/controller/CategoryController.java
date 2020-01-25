package com.myclass.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Categories;
import com.myclass.service.CategoryService;
import com.myclass.service.GenericService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
	@Autowired
	GenericService<Categories, Integer> genericService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public String index(ModelMap model) {
		model.addAttribute("categories", categoryService.findAll());
		return "categories/index";
	}

	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("category", new Categories());
		return "categories/add";
	}

	@PostMapping("add")
	public String add(@Valid @ModelAttribute("category") Categories category, BindingResult errors) {

		if (errors.hasErrors()) {
			return "/categories/add";
		}

		categoryService.add(category);

		return "redirect:/admin/categories";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {

		Categories category = genericService.findById(id);
		
		model.addAttribute("category", category);
		
		return "categories/edit";
	}
	
	@PostMapping("edit")
	public String edit(Model model, @Valid @ModelAttribute("category") Categories category, BindingResult errors) {
		return null;	
	}
}
