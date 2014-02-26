package com.nevo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nevo.domain.User;
import com.nevo.service.IUserService;
import com.nevo.viewModel.UserVm;

/**
 * UserApiController handles API requests (producing objects to be rendered as JSON in body)
 * 
 * @author poo
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserApiController {

	@Autowired
	private IUserService userService;
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> getAllUsers(){
		return userService.findAll();
	}

	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public UserVm getUser(@PathVariable String username) {
		User user = userService.findByUsername(username);
		
		if (null != user) {
			return new UserVm(user);
		} else {
			return null;
		}
	}
}
