package com.myclass.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Categories;
import com.myclass.repository.CategoryRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class CategoryRepositoryImpl extends GenericRepositoryImpl<Categories, Integer>
									implements CategoryRepository{

	@SuppressWarnings("rawtypes")
	public void update(Categories category) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE categories set title = :title, icon = :icon " + "where id = :id");
		query.setParameter("title", category.getTitle());
		query.setParameter("icon", category.getIcon());
		query.setParameter("id", category.getId());
		query.executeUpdate();
	}
	
}
