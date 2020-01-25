package com.myclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Categories;
import com.myclass.repository.CategoryRepository;
import com.myclass.service.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Categories, Integer>
								 implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	public void edit(Categories category) {
		categoryRepository.update(category);
	}

}
