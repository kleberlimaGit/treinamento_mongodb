package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/titlesearch")
	public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value="text",defaultValue = "") String text){
		List<PostDTO> list = postService.findByTitle(text);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value="/fullsearch")
 	public ResponseEntity<List<PostDTO>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="start", defaultValue="") String start,
 			@RequestParam(value="end", defaultValue="") String end) {
		List<PostDTO> list = postService.fullSearch(text, start, end);
		return ResponseEntity.ok().body(list);
	}
}
