package com.myclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.entity.Categories;
import com.myclass.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryApiController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<List<Categories>> findAllCategory() {
		List<Categories> categories = categoryService.findAll();
		if(categories.isEmpty()) {
			return new ResponseEntity<List<Categories>>(HttpStatus.NO_CONTENT);					
		}
		return new ResponseEntity<List<Categories>>(categories, HttpStatus.OK);
	}
}
