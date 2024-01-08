package com.example.demo.models.embedded;

import com.example.demo.models.entities.User;

public class Author {
	
	private String id;
	private String name;
	
	
	public Author() {}
	public Author(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Author(User user) {
		this.id = user.getId();
		this.name = user.getName();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
