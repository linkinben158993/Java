package com.myclass.repository;

import java.util.List;

public interface GenericRepository<T , K> {
	List<T> findAll();
	T findById(K id);	
	void insert(T object);
	int removeById(K id);
}
