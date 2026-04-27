package it.aulab.esercizio_spring_data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.esercizio_spring_data.models.Post;
import it.aulab.esercizio_spring_data.repositories.PostRepository;
import it.aulab.esercizio_spring_data.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
}
