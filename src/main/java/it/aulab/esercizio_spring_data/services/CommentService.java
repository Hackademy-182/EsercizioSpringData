package it.aulab.esercizio_spring_data.services;

import java.util.List;

import it.aulab.esercizio_spring_data.models.Comment;

public interface CommentService {
    List<Comment> readAll();
    Comment read(Long id);
    List<Comment> read(String email);
    Comment create(Comment comment);
    Comment update(Long id, Comment comment);
    void delete(Long id);
}
