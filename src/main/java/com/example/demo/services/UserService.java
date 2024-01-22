package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(x -> new UserDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(String id) {
		User user = getUserById(id);
		return new UserDTO(user);
	}

	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = userRepository.insert(entity);
		return new UserDTO(entity);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(String id) {
		getUserById(id);
		userRepository.deleteById(id);
	}
	
	@Transactional
	public UserDTO update(String id, UserDTO dto) {
		User user = getUserById(id);
		copyDtoToEntity(dto, user);
		user = userRepository.save(user);
		return new UserDTO(user);
	}

	@Transactional(readOnly = true)
	public List<PostDTO> getUserPosts(String id) {
		User user = getUserById(id);
		return user.getPosts().stream().map(x -> new PostDTO(x)).toList();
	}

	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}

	private User getUserById(String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
		return user;
	}
}
