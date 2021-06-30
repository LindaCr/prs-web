package com.prs.db;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.User;

public interface UserRepo extends CrudRepository<User, Integer> {

	List<User> findAllById(int id);
	
}
