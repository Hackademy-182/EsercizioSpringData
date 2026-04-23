package it.aulab.esercizio_spring_data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.aulab.esercizio_spring_data.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

    
    List<Post> findByTitle(String title);

    List<Post> findByBody(String body);

    List<Post> findByTitleAndBody(String title, String body);

    @Query(value = "SELECT * FROM posts p WHERE p.title = 'Titolo post'", nativeQuery = true)
    List<Post> postsWithSameTitle();

    @Query("SELECT p FROM Post p WHERE p.title = 'Titolo post'")
    List<Post> samePostTitleNonNative();
}