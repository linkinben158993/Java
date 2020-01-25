package com.myclass.service.impl;

import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.dto.ChangePasswordDto;
import com.myclass.entity.Users;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<Users, String> implements UserService {

	@Autowired
	private UserRepository userRepository;

	public void edit(Users user) {
		userRepository.update(user);
	}

	public int editPassword(ChangePasswordDto user) {
		return userRepository.updatePassword(user);
	}

//	public List<Users> findAll() {
//	return userRepository.findAll();
//}

//public Users findById(String id) {
//	return userRepository.findById(id);
//}

public void add(Users user) {
	String id = UUID.randomUUID().toString();
	user.setId(id);
	String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
	user.setPassword(hashed);
	userRepository.insert(user);
}

//	public int delete(String id) {
//		if (userRepository.removeById(id) == 1) {
//			return 1;
//		}
//		return 0;
//	}
}
