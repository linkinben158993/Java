package com.myclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Roles;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Roles, String> implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public void edit(Roles role) {
		roleRepository.update(role);
	}

//	public List<Roles> findAll() {
//	return roleRepository.findAll();
//}
//
//public Roles findById(String id) {
//	return roleRepository.findById(id);
//}

//public void add(Roles role) {
//	String id = UUID.randomUUID().toString();
//	role.setId(id);
//	roleRepository.insert(role);
//}

//public int delete(String id) {
//	if (roleRepository.removeById(id) == 1) {
//		return 1;
//	}
//	return 0;
//}
}
