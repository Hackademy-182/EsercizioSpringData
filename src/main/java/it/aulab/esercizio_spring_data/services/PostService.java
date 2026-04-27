package it.aulab.esercizio_spring_data.services;

import java.util.List;

import it.aulab.esercizio_spring_data.models.Post;

public interface PostService {
    List<Post> readAll();
    Post read(Long id);
    List<Post> readByTitle(String title);
    List<Post> readByBody(String body);
    List<Post> read(String title, String body);
    Post create(Post post);
    Post update(Long id, Post post);
    void delete(Long id);
}

// In AuthorService permetteva di mettere tante funzioni di nome read, qui mi ha costretto a cambiare alcuni nomi, altrimenti mi dava errore. Non capisco la differenza. Forse perchè title e body hanno entrambi una stringa come parametro?
