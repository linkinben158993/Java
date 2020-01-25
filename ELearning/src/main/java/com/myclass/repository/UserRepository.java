package com.myclass.repository;

import com.myclass.dto.ChangePasswordDto;
import com.myclass.entity.Users;

public interface UserRepository extends GenericRepository<Users, String> {
	void update(Users user);
	int updatePassword(ChangePasswordDto user);
	
	Users findByEmail(String email);
}
