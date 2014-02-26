package com.nevo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nevo.domain.RESTOperationStatus;
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
	
	@RequestMapping(value="/id/{userid}", method=RequestMethod.GET)
	public UserVm getUserById(@PathVariable String userid) {
		
		User user = userService.findById(userid);
		
		if (null != user) {
			return new UserVm(user);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value="/create/{username}/{password}", method=RequestMethod.POST)
	public UserVm createUser(@PathVariable String username, @PathVariable String password) {
				
		User user = userService.create(new User(username, password));
		
		return getUserById(user.getId());

	}
	
	@RequestMapping(value="/delete/{userid}", method=RequestMethod.DELETE)
	public RESTOperationStatus deleteUser(@PathVariable String userid) {
		
		boolean success = true;
		String message = "User deleted successfully: " + userid;
		
		try {
			userService.delete(userid);
		} catch (Exception e) {
			success = false;
			message = "Could not delete user: " + userid + ". Exception: "+ e.getClass().getName() + ": " + e.getMessage();
		}
		
		return new RESTOperationStatus(success, message);
	}
}
