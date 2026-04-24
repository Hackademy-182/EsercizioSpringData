package it.aulab.esercizio_spring_data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.aulab.esercizio_spring_data.models.Author;
import it.aulab.esercizio_spring_data.repositories.AuthorRepository;

@Controller
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(value="/authors", method = RequestMethod.GET)
    public @ResponseBody List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
}
