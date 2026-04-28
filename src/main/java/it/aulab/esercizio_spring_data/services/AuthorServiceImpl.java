package it.aulab.esercizio_spring_data.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.esercizio_spring_data.dtos.AuthorDTO;
import it.aulab.esercizio_spring_data.models.Author;
import it.aulab.esercizio_spring_data.models.Post;
import it.aulab.esercizio_spring_data.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<AuthorDTO> readAll() {
        List<AuthorDTO> dtos = new ArrayList<AuthorDTO>();
        for (Author author : authorRepository.findAll()) {
            dtos.add(mapper.map(author, AuthorDTO.class));
        }
        return dtos;
    }

    @Override
    public AuthorDTO read(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            return mapper.map(optionalAuthor.get(), AuthorDTO.class);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author id: " + id + " not found");
        }
    }

    @Override
public List<AuthorDTO> read(String email) {

    if (email == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
    } else {

        List<AuthorDTO> dtos = new ArrayList<AuthorDTO>();

        for (Author author : authorRepository.findByEmail(email)) {
            dtos.add(mapper.map(author, AuthorDTO.class));
        }

        return dtos;
    }
}

    @Override
    public List<AuthorDTO> read(String name, String surname) {
        if (name == null || surname == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else{
            List<AuthorDTO> dtos = new ArrayList<AuthorDTO>();
            for (Author author : authorRepository.findByNameAndSurname(name, surname)) {
                dtos.add(mapper.map(author, AuthorDTO.class));
            }
            return dtos;
        }
    }

    @Override
    public AuthorDTO create(Author author) {
        if (author.getName() == null || author.getSurname() == null || author.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            // Io avevo messo nullable = false in tutti questi attributi dell'autore
        }else{
            return mapper.map(authorRepository.save(author), AuthorDTO.class);
        }
    }

    @Override
    public AuthorDTO update(Long id, Author author) {
        if (authorRepository.existsById(id)) {
            author.setId(id);
            return mapper.map(authorRepository.save(author), AuthorDTO.class);
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
