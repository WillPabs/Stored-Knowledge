package com.pabitero.booksdemo.controller.rest;

import com.pabitero.booksdemo.entity.Author;
import com.pabitero.booksdemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@CrossOrigin("*")
public class AuthorRestController {

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

}
