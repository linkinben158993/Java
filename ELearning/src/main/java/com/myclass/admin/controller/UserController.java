package com.myclass.admin.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.dto.CustomUserDetails;
import com.myclass.entity.Users;
import com.myclass.service.GenericService;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {

	@Autowired
	GenericService<Users, String> genericService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserService userService;
	@Autowired
	UserDetailsService userDetailsService;

	@GetMapping("")
	public String index(Model model) {

		model.addAttribute("roles", roleService.findAll());

		model.addAttribute("users", genericService.findAll());

		return "users/index";
	}

	@GetMapping("add")
	public String add(Model model) {

		model.addAttribute("roles", roleService.findAll());

		model.addAttribute("user", new Users());

		return "users/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("user") Users user, BindingResult errors) {

		if (errors.hasErrors()) {

			model.addAttribute("roles", roleService.findAll());

			return "users/add";
		}

		genericService.add(user);

		return "redirect:/admin/users";
	}

	//Hàm edit người dùng, đã phân quyền người dùng chỉ có thể tự edit bản thân mình, nhưng không được edit quyền, 
	//Admin có quyền edit quyền của người khác.
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") String id, Model model, Principal principal) {
		
		Users user = genericService.findById(id);
		
		System.out.println(user.getEmail());
		System.out.println(principal.getName());
		
		CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(principal.getName());
		
		System.out.println(userDetails.getAuthorities().toString());
		
		if (user.getEmail().equals(principal.getName()) || userDetails.getAuthorities().toString().equals("[ROLE_ADMIN]")) {

			model.addAttribute("roles", roleService.findAll());

			model.addAttribute("user", user);

			return "users/edit";
		}
		
		else {
			return "error/403";
		}
	}

	@PostMapping("edit")
	public String edit(Model model, @ModelAttribute("user") Users user, BindingResult errors) {

		if (errors.hasErrors()) {

			System.out.println(errors);

			return "users/edit";
		}

		userService.edit(user);

		return "redirect:/admin/users";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") String id) {

		genericService.delete(id);

		return "redirect:/admin/users";
	}
}
