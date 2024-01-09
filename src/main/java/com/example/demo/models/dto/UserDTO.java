package com.example.demo.models.dto;

import org.springframework.beans.BeanUtils;

import com.example.demo.models.entities.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
	private String id;
	@NotBlank(message = "{name.not.blank}")
	@Size(message = "{name.size.message}", min = 3)
	private String name;
	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.valid}")
	private String email;

	public UserDTO() {
	}

	public UserDTO(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public UserDTO(User user) {
		BeanUtils.copyProperties(user, this);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
