package com.myclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.ChangePasswordDto;
import com.myclass.entity.Users;
import com.myclass.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserApiController {
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<Users>> findAllUser() {
		List<Users> users = userService.findAll();
		if(users.isEmpty()) {
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);					
		}
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("changePassword")
	ResponseEntity changePassword(@RequestBody ChangePasswordDto changePassword) {
		if(userService.editPassword(changePassword) == 1) {
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

}
