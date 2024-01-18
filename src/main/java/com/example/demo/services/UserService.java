package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> findAll(){
		return userRepository.findAll().stream().map(x -> new UserDTO(x)).toList();
	}
	
	public UserDTO findById(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: "+id));
		return new UserDTO(user);
	}
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = userRepository.insert(entity);
		return new UserDTO(entity);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public UserDTO update(String id, UserDTO dto) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: "+id));
		copyDtoToEntity(dto, user);
		user = userRepository.save(user);
		return new UserDTO(user);
	}
	
	public List<PostDTO> getUserPosts(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: "+id));
		return user.getPosts().stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
