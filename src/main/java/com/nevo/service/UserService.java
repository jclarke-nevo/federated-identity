package com.nevo.service;

import java.util.UUID;

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
		return userRepository.findOne("");
	}
	
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User create(User user)
	{
		user.setId(UUID.randomUUID().toString());
		
		return userRepository.save(user);
	}
	
}