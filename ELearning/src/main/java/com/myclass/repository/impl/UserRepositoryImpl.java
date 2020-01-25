package com.myclass.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myclass.dto.ChangePasswordDto;
import com.myclass.entity.Users;
import com.myclass.repository.UserRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class UserRepositoryImpl extends GenericRepositoryImpl<Users, String> implements UserRepository {	
	@SuppressWarnings("rawtypes")
	public void update(Users user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"UPDATE users set email = :email, fullname = :fullname , role_id = :role_id " + "where id = :id");
		try {
			query.setParameter("email", user.getEmail());
			query.setParameter("fullname", user.getFullname());
			query.setParameter("role_id", user.getRole_id());
			query.setParameter("id", user.getId());
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("rawtypes")
	public int updatePassword(ChangePasswordDto user) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE users SET " + "password = :password, " + "WHERE email = :email";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("password", user.getPassword());
			query.setParameter("email", user.getEmail());

			return query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Users findByEmail(String email) {
		String hql = "FROM users where email = :email";
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Users> query = session.createQuery(hql, Users.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
