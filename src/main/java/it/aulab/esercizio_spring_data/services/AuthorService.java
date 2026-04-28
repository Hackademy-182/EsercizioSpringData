package it.aulab.esercizio_spring_data.services;

import java.util.List;

import it.aulab.esercizio_spring_data.dtos.AuthorDTO;
import it.aulab.esercizio_spring_data.models.Author;

public interface AuthorService {
    List<AuthorDTO>readAll();
    AuthorDTO read(Long id);
    List<AuthorDTO>read(String email);
    List<AuthorDTO>read(String name, String surname);
    AuthorDTO create(Author author);
    AuthorDTO update(Long id, Author author);
    void delete(Long id);
}
