package it.aulab.esercizio_spring_data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import it.aulab.esercizio_spring_data.models.Author;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {
    List<Author> findByName(String name);

    List<Author> findBySurname(String surname);

     List<Author> findByEmail(String email);

    List<Author> findByNameAndSurname(String name, String surname);

    @Query(value = "SELECT * FROM authors a WHERE a.name = 'Valentino'", nativeQuery = true)
    List<Author> authorsWithSameName();

    @Query(value = "SELECT a FROM Author a WHERE a.name = 'Valentino'")
    List<Author> sameAuthorNameNonNative();
}
