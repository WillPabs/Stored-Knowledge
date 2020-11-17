package com.pabitero.booksdemo.bootstrap;

import com.pabitero.booksdemo.entity.Author;
import com.pabitero.booksdemo.entity.Book;
import com.pabitero.booksdemo.entity.Publisher;
import com.pabitero.booksdemo.service.AuthorService;
import com.pabitero.booksdemo.service.BookService;
import com.pabitero.booksdemo.service.PublisherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private AuthorService authorService;
    private BookService bookService;
    private PublisherService publisherService;

    public BootStrapData(AuthorService authorService, BookService bookService, PublisherService publisherService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    @Override
    public void run(String... args) throws Exception {
        Author will = new Author("William", "Pabitero");
        Book book1 = new Book("How to Code", "1112");
        Publisher pub1 = new Publisher("pub1", "67-14 41st Ave", "Woodside", "NY", "11377");
        publisherService.savePublisher(pub1);

        will.getBooks().add(book1);
        book1.getAuthors().add(will);

        book1.setPublisher(pub1);
        pub1.getBooks().add(book1);

        Author elon = new Author("Elon", "Musk");
        Author bill = new Author("Bill", "Gates");
        Author jeff = new Author("Jeff", "Bezos");
        Book book2 = new Book("Super Rocket Manual", "123423");
        Book book3 = new Book("Microsoft: The Legacy", "3412");
        Publisher pub2 = new Publisher("NY Publishing", "41st W", "New York", "NY", "11153");
        publisherService.savePublisher(pub2);

        authorService.saveAuthor(elon);
        authorService.saveAuthor(bill);
        authorService.saveAuthor(jeff);

        bookService.saveBook(book2);
        bookService.saveBook(book3);

        authorService.saveAuthor(will);
        bookService.saveBook(book1);
        publisherService.savePublisher(pub1);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books " + bookService.getBooks().size());
        System.out.println("Publisher Count " + publisherService.getPublishers().size());
        System.out.println("Publisher 1 Number of Books " + pub1.getBooks().size());

//        List<Author> authors = authorService.getAuthors();
//        authors.stream().forEach(author -> System.out.println(author.getBooks().toString()));

    }
}
