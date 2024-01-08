package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.models.entities.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
