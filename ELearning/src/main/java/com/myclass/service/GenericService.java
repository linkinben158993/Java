package com.myclass.service;

import java.util.List;

public interface GenericService<T, K> {
	List<T> findAll();
	T findById(K id);
	void add(T object);
	int delete(K id);
}
