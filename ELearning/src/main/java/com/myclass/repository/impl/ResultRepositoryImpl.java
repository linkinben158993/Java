package com.myclass.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Results;
import com.myclass.repository.ResultRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class ResultRepositoryImpl extends GenericRepositoryImpl<Results, String>
								  implements ResultRepository{

	public void update(Results result) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	}
}
