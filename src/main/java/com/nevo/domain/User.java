package com.nevo.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
	public User(){}
	
	public User( String username, String password) {
		this.password = password;
		this.username = username;
	}

	@Id
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	private int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	private List<AlternateId> alternateIds;
	public List<AlternateId> getAlternateIds() {
		return alternateIds;
	}
	public void setAlternateIds(List<AlternateId>alternateIds) {
		this.alternateIds = alternateIds;
	}
}
