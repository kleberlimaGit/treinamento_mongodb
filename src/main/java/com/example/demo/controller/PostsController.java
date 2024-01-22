package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dto.PostDTO;
import com.example.demo.services.PostService;

@RestController
@RequestMapping("posts")
public class PostsController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id){
		PostDTO dto = postService.findById(id);
		return ResponseEntity.ok(dto);
	}
}
