package com.pabitero.booksdemo.service;

import com.pabitero.booksdemo.entity.Author;
import com.pabitero.booksdemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> saveAuthors(List<Author> authors) {
        return authorRepository.saveAll(authors);
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author getAuthorByfirstName(String firstName) {
        return authorRepository.findByFirstName(firstName);
    }

    public Author getAuthorBylastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }

    public String deleteAuthor(Long id) {
        authorRepository.deleteById(id);
        return "Author #: " + id + " removed";
    }

    public Author updateAuthor(Author author) {
        Author existingAuthor = authorRepository.findById(author.getId()).orElse(author);
        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());
        existingAuthor.setBooks(author.getBooks());
        return authorRepository.save(existingAuthor);
    }
}