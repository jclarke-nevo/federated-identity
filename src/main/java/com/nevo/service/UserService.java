package com.nevo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevo.domain.User;
import com.nevo.repository.IUserRepository;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User findById(String id) {
		return userRepository.findById(id);
	}
	
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User create(User user)
	{	
		return userRepository.save(user);
	}
	
}
