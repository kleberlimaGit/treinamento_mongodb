package com.example.demo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dto.PostDTO;
import com.example.demo.models.entities.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public PostDTO findById(String id) {
		Optional<Post> result = repository.findById(id);
		Post entity = result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
		return new PostDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PostDTO> findByTitle(String text) {
		List<Post> list =  repository.searchTitle(text);
//		List<Post> list =  repository.findByTitleContainingIgnoreCase(text);
		return list.stream().map(x -> new PostDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public List<PostDTO> fullSearch(String text, String start, String end) {
		String startMoment = convertToValidDate(start, LocalDate.ofEpochDay(0L));
		String endMoment = convertToValidDate(end,LocalDate.now());
		List<Post> list = repository.fullSearch(text, startMoment, endMoment);
		return list.stream().map(x -> new PostDTO(x)).toList();
	}
	
	private String convertToValidDate(String date, LocalDate alternativeDate) {

		    DateTimeFormatter dateTimeFormatter = DateTimeFormatter
		    .ofPattern("yyyy-mm-dd");
		    try {
		        return LocalDate.parse(date, dateTimeFormatter).toString();
		    } catch (DateTimeParseException e) {
		       return alternativeDate.toString();
		    } 		
	}
	
}
