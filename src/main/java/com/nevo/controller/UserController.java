package com.nevo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nevo.domain.User;
import com.nevo.service.IUserService;
import com.nevo.viewModel.UserVm;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAllUsers(){
		return "/users/index/foobar".toString();
	}

	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public @ResponseBody UserVm getUser(@PathVariable String username) {
		User user = userService.findByUsername(username);
		
		if (null != user) {
			return new UserVm(user);
		} else {
			return null;
		}
	}
	
}
