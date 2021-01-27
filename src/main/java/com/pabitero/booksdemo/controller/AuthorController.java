package com.pabitero.booksdemo.controller;

import com.pabitero.booksdemo.entity.Author;
import com.pabitero.booksdemo.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/authors")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthors(Model model) {
        log.info("Inside getAuthors in AuthorController");
        model.addAttribute("authors", authorService.getAuthors());
        return "books/authorList";
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthorsBooks(@PathVariable Long id,
                                  Model model) {
        log.info("Inside getAuthorsBooks in AuthorController");

        if (authorService.getAuthorById(id) != null) {
            log.info("Generate singular author");
            Author existingAuthor = authorService.getAuthorById(id);
            model.addAttribute("author", existingAuthor);
            model.addAttribute("books", existingAuthor.getBooks());
            return "books/authorBooks";

        } else {
            return "books/errorPage";
        }
    }

    @GetMapping("/create")
    public String createAuthor(Model model) {
        log.info("Inside createAuthor in AuthorController");
        model.addAttribute("author", new Author());

        return "books/authorCreator";
    }

    @PostMapping(value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String post(Author author) {
        log.info("Inside saveNewAuthor in AuthorController");
        try {
            authorService.saveAuthor(author);

        } catch (Exception e) {
            log.error(e.getMessage());
            return "books/error";
        }
        return "books/result";
    }

    @PutMapping("/")
    public String updateAuthor(@RequestBody Author author) {
        log.info("Inside updateAuthor in AuthorController");
        if (author.getId() != null) {
            authorService.updateAuthor(author);
            return "redirect:/authors";
        } else {
            return "books/errorPage";
        }
    }
}
