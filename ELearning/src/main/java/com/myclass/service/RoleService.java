package com.myclass.service;

import com.myclass.entity.Roles;

public interface RoleService extends GenericService<Roles, String> {
//	List<Roles> findAll();

//	Roles findById(String id);

//	void add(Roles role);

//	int delete(String id);

	void edit(Roles role);
}
