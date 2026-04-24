package it.aulab.esercizio_spring_data.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.esercizio_spring_data.models.Comment;
import it.aulab.esercizio_spring_data.repositories.CommentRepository;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
}
