package it.aulab.esercizio_spring_data.services;

import java.util.List;

import it.aulab.esercizio_spring_data.models.Author;

public interface AuthorService {
    List<Author>readAll();
    Author read(Long id);
    List<Author>read(String email);
    List<Author>read(String name, String surname);
    Author create(Author author);
    Author update(Long id, Author author);
    void delete(Long id);
}
