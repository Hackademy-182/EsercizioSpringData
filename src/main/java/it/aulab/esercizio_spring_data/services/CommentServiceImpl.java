package it.aulab.esercizio_spring_data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.esercizio_spring_data.models.Comment;
import it.aulab.esercizio_spring_data.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> readAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment read(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            return optionalComment.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id: " + id + " not found");
        }
    }

    @Override
    public List<Comment> read(String email) {
       if (email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }else{
            return commentRepository.findByEmail(email);
       }
    }

    @Override
    public Comment create(Comment comment) {
        if (comment.getEmail() == null || comment.getBody() == null) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return commentRepository.save(comment);
        }
    }

    @Override
    public Comment update(Long id, Comment comment) {
        if (commentRepository.existsById(id)) {
            comment.setId(id);
            return commentRepository.save(comment);
        }else{
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        }else{
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
