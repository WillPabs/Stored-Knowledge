package com.pabitero.booksdemo.controller;

import com.pabitero.booksdemo.entity.Author;
import com.pabitero.booksdemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.getAuthors());

        return "books/authorList";
    }

    @GetMapping("/authors-create")
    public String createAuthor(Model model) {
        model.addAttribute("author", new Author());

        return "books/authorCreator";
    }

    @PostMapping("/saveAuthor")
    public String saveNewAuthor(@ModelAttribute Author author) {
        authorService.saveAuthor(author);

        return "books/result";
    }

    @GetMapping("/authors/{id}/books")
    public String getAuthorsBooks(@PathVariable Long id, Model model) {
        Author existingAuthor = authorService.getAuthorById(id);
        if(existingAuthor != null) {
            model.addAttribute("author", existingAuthor);
            model.addAttribute("books", existingAuthor.getBooks());
        }
        return "books/authorBooks";
    }
}
