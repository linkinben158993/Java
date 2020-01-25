package com.myclass.repository;

import com.myclass.entity.Results;

public interface ResultRepository extends GenericRepository<Results, String>{
	void update(Results result);
}
