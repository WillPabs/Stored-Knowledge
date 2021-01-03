package com.pabitero.booksdemo.controller;

import com.pabitero.booksdemo.entity.Book;
import com.pabitero.booksdemo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getBooks(Model model) {
        log.info("Inside getBooks in BookController");
        model.addAttribute("books", bookService.getBooks());
        return "books/list";
    }

    @GetMapping("/{id}")
    public String getBookById(Model model, @PathVariable Long id) {
        log.info("Inside getBookById in BookController");
        Book foundBook;
        try {
            foundBook = bookService.getBookById(id);

            if(!foundBook.equals(Book.class) || foundBook == null) {
                return "books/errorPage";
            } else {
                model.addAttribute("book", foundBook);
                model.addAttribute("authors", foundBook.getAuthors());
                model.addAttribute("publisher", foundBook.getPublisher());
                return "books/bookPage";
            }
        } catch (NullPointerException e) {
            log.error(e.getMessage());
        }
        return "books/errorPage";
    }
}
