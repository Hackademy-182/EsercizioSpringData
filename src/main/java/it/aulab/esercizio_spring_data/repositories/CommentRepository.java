package it.aulab.esercizio_spring_data.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.aulab.esercizio_spring_data.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByEmail(String email);
}
