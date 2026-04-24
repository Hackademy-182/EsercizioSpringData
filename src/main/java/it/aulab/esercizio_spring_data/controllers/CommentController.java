package it.aulab.esercizio_spring_data.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.aulab.esercizio_spring_data.models.Comment;
import it.aulab.esercizio_spring_data.repositories.CommentRepository;

@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public @ResponseBody List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
}
