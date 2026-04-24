package it.aulab.esercizio_spring_data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import it.aulab.esercizio_spring_data.models.Comment;

public interface CommentRepository extends ListCrudRepository<Comment, Long> {
    List<Comment> findByEmail(String email);

    @Query(value = "SELECT * FROM comments c WHERE c.email = 'tizio@boh.it'", nativeQuery=true)
    List<Comment> commentWithSameEmail();

    @Query(value = "SELECT c FROM Comment c WHERE c.email = 'tizio@boh.it'")
    List<Comment> commentWithSameEmailNonNative();
}
