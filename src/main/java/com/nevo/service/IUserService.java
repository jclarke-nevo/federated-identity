package com.nevo.service;

import java.util.List;

import com.nevo.domain.AlternateId;
import com.nevo.domain.User;

public interface IUserService {

	public User findById(String id);

	public User findByUsername(String username);

	public List<User> findAll();

	public User create(User user);

	public abstract void delete(String userId);
	
	public void addAlternateId(String userId, AlternateId alternateId);

}