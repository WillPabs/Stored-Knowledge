package com.pabitero.booksdemo.controller;

import com.pabitero.booksdemo.entity.Author;
import com.pabitero.booksdemo.entity.Book;
import com.pabitero.booksdemo.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getAuthorsBooks(@PathVariable(required = false) Long id,
                                  Model model) {
        log.info("Inside getAuthorsBooks in AuthorController");

        if(id == null) {
            model.addAttribute("authors", authorService.getAuthors());
            return "books/authorList";

        } else if(authorService.getAuthorById(id) != null) {
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

    @PostMapping("/")
    public String saveNewAuthor(@RequestBody Author author) {
        log.info("Inside saveNewAuthor in AuthorController");
        authorService.saveAuthor(author);

        return "books/result";
    }

    @PutMapping("/")
    public String updateAuthor(@RequestBody Author author) {
        log.info("Inside updateAuthor in AuthorController");
        if(author.getId() != null) {
            authorService.updateAuthor(author);
            return "redirect:/authors";
        } else {
            return "books/errorPage";
        }
    }
}
