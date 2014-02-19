package com.nevo.service;

import com.nevo.domain.User;

public interface IUserService {

	public User findById(String id);

	public User findByUsername(String username);

	public User create(User user);

}