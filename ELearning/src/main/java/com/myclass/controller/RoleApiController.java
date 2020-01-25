package com.myclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Roles;
import com.myclass.service.RoleService;

@RestController
@RequestMapping("api/roles")
public class RoleApiController {
	@Autowired
	RoleService roleService;
	
	@GetMapping
	public ResponseEntity<List<Roles>> findAllRoles(){
		List<Roles> roles = roleService.findAll();
		if(roles.isEmpty()) {
			return new ResponseEntity<List<Roles>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Roles>>(roles, HttpStatus.OK);
	}
}
