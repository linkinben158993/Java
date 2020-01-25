package com.myclass.dao;

import java.util.List;

public interface IDAO {
	public List<?> findAll();
	public Class<?> findById(int id);
	public int add(Class<?> entity);
	public int delete(Class<?> entity);
	public int update(Class<?> entity);
}
