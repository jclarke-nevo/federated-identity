package com.nevo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nevo.domain.User;
import com.nevo.service.IUserService;
import com.nevo.viewModel.UserVm;

/**
 * UserController handles non-API requests (rendering HTML results)
 * 
 * @author John C
 *
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listAllUsers(){
		List<User> users = userService.findAll();
		List<UserVm> userModels = new ArrayList<UserVm>();
		for (User user : users) {
			userModels.add(new UserVm(user));
		}
		ModelAndView mv = new ModelAndView("/users/index", "UserList", userModels);
		return mv;
	}
}
