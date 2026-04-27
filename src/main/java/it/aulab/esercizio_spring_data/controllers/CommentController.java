package it.aulab.esercizio_spring_data.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.esercizio_spring_data.models.Comment;
import it.aulab.esercizio_spring_data.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.readAll();
    }

    @GetMapping("{id}")
    public Comment getComment(@PathVariable ("id") Long id){
        return commentService.read(id);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment){
        return commentService.create(comment);
    }

    @PutMapping("{id}")
    public Comment updatComment(@PathVariable ("id") Long id, @RequestBody Comment comment){
        return commentService.update(id, comment);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable ("id") Long id){
        commentService.delete(id);
    }
}
