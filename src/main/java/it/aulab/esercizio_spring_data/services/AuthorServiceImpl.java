package it.aulab.esercizio_spring_data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.esercizio_spring_data.models.Author;
import it.aulab.esercizio_spring_data.models.Post;
import it.aulab.esercizio_spring_data.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> readAll() {
         return authorRepository.findAll();
    }

    @Override
    public Author read(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author id: " + id + " not found");
        }
    }

    @Override
    public List<Author> read(String email) {
        if (email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return authorRepository.findByEmail(email);
        }
    }

    @Override
    public List<Author> read(String name, String surname) {
        if (name == null || surname == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            return authorRepository.findByNameAndSurname(name, surname);
        }
    }

    @Override
    public Author create(Author author) {
        if (author.getName() == null || author.getSurname() == null || author.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            // Io avevo messo nullable = false in tutti questi attributi dell'autore
        }else{
            return authorRepository.save(author);
        }
    }

    @Override
    public Author update(Long id, Author author) {
        if (authorRepository.existsById(id)) {
            author.setId(id);
            return authorRepository.save(author);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        if (authorRepository.existsById(id)) {
            Author author = authorRepository.findById(id).get();
            List<Post> authorPosts = author.getPosts();
            for(Post post : authorPosts){
                post.setAuthor(null);
            }
            authorRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID dell'autore non trovato!");
        }
    }
    
}
