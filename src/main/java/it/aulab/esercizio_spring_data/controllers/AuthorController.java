package it.aulab.esercizio_spring_data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.esercizio_spring_data.models.Author;
import it.aulab.esercizio_spring_data.repositories.AuthorRepository;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping("{id}")
    public Author getAuthor(@PathVariable ("id") Long id){
        return authorRepository.findById(id).get();
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author){
        return authorRepository.save(author);
    }
}
