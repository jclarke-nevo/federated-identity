package com.nevo.viewModel;

import com.nevo.domain.User;

public class UserVm {
	
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public UserVm(){
		
	}

	public UserVm(User user){
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
	}
	
	public User CreateDomainModel(){
		User user = new User();

		user.setId(this.getId());
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());
		
		return user;
	}

}
