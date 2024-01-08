package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Helena", "maria@gmail.com");
		User alex = new User(null, "Alex Duarte", "alex@gmail.com");
		User bob = new User(null, "Bob Bobão", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
	}
}
