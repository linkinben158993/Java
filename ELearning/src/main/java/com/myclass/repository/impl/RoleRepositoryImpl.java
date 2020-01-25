package com.myclass.repository.impl;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Roles;
import com.myclass.repository.RoleRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class RoleRepositoryImpl extends GenericRepositoryImpl<Roles, String> implements RoleRepository{
	@SuppressWarnings("rawtypes")
	public void update(Roles role) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE roles set name = :name, description = :description " + "where id = :id");
		query.setParameter("name", role.getName());
		query.setParameter("description", role.getDescription());
		query.setParameter("id", role.getId());
		query.executeUpdate();
		/*
		 * if (result < 1) { System.out.println("Cập nhật thất bại!"); } else {
		 * System.out.println("Cập nhật thành công!"); }
		 */
	}
}
