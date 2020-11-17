package com.pabitero.booksdemo.service;

import com.pabitero.booksdemo.entity.Book;
import com.pabitero.booksdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> saveBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public String deleteBook(Long id) {
        bookRepository.deleteById(id);
        return "Book #: " + id + " removed";
    }

    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId()).orElse(book);
        existingBook.setTitle(book.getTitle());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setAuthors(book.getAuthors());
        return bookRepository.save(existingBook);
    }
}
