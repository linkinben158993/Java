package com.myclass.service;

import com.myclass.entity.Categories;

public interface CategoryService extends GenericService<Categories, Integer>{
	void edit(Categories category);
}
