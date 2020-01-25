package com.myclass.repository;

import com.myclass.entity.Roles;

public interface RoleRepository extends GenericRepository<Roles, String> {
	void update(Roles role);
}
