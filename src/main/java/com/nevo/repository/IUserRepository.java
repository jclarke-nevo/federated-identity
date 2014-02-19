package com.nevo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nevo.domain.User;

public interface IUserRepository extends MongoRepository<User, String> {

	public abstract User findByUsername(String username);

}