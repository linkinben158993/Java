package com.myclass.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Roles;
import com.myclass.service.GenericService;
import com.myclass.service.RoleService;

@Controller
@RequestMapping("admin/roles")
public class RoleController{

	@Autowired
	GenericService<Roles, String> genericService;
	@Autowired
	RoleService roleService;

	@GetMapping("")
	public String index(Model model) {

		model.addAttribute("roles", genericService.findAll());

		return "/roles/index";
	}

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("role", new Roles());
		return "roles/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("role") Roles role, BindingResult errors) {

		if (errors.hasErrors()) {
			return "roles/add";
		}

		genericService.add(role);

		return "redirect:/admin/roles";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") String id, Model model) {

		Roles role = genericService.findById(id);

		model.addAttribute("role", role);

		return "roles/edit";
	}

	@PostMapping("edit")
	public String edit(Model model, @Valid @ModelAttribute("role") Roles role, BindingResult errors) {

		if (errors.hasErrors()) {
			return "roles/edit";
		}

		roleService.edit(role);

		return "redirect:/admin/roles";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") String id) {

		genericService.delete(id);

		return "redirect:/admin/roles";
	}
}
