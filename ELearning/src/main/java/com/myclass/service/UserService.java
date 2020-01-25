package com.myclass.service;

import com.myclass.dto.ChangePasswordDto;
import com.myclass.entity.Users;

public interface UserService extends GenericService<Users, String> {
//	List<Users> findAll();

//	Users findById(String id);

//	void add(Users role);

//	int delete(String id);

	void edit(Users user);
	int editPassword(ChangePasswordDto user);
}
