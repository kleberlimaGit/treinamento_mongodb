package com.example.demo.services;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.entities.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	@Transactional(readOnly = true)
	public PostDTO findById(String id) {
		Optional<Post> result = repository.findById(id);
		Post entity = result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
		return new PostDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PostDTO> findByTitle(String text) {
		List<Post> list =  repository.searchTitle(text);
		return list.stream().map(x -> new PostDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public List<PostDTO> fullSearch(String text, String start, String end) {
		Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
		Instant endMoment = convertMoment(end, Instant.now());
		List<Post> list = repository.fullSearch(text, startMoment, endMoment);
		return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	private Instant convertMoment(String orignalText, Instant alternative) {
		try {
			return Instant.parse(orignalText);
		}
		catch (DateTimeParseException e) {
			return alternative;
		}
	}
}
