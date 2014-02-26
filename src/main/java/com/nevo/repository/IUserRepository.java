package com.nevo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nevo.domain.User;

public interface IUserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
	
	public User findById(String id);

}