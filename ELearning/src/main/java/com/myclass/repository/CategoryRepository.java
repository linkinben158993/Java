package com.myclass.repository;

import com.myclass.entity.Categories;

public interface CategoryRepository extends GenericRepository<Categories, Integer>{
	void update(Categories category);
}
