package it.aulab.esercizio_spring_data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.aulab.esercizio_spring_data.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByName(String name);

    List<Author> findBySurname(String surname);

    List<Author> findByNameAndSurname(String name, String surname);

    @Query(value = "SELECT * FROM authors a WHERE a.name = 'Valentino'", nativeQuery = true)
    List<Author> authorsWithSameName();

    @Query(value = "SELECT * FROM authors a WHERE a.name = 'Valentino'")
    List<Author> sameAuthorNameNonNative();
}
