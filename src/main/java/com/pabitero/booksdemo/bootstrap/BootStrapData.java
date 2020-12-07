package com.pabitero.booksdemo.bootstrap;

import com.pabitero.booksdemo.entity.Author;
import com.pabitero.booksdemo.entity.Book;
import com.pabitero.booksdemo.entity.Publisher;
import com.pabitero.booksdemo.service.AuthorService;
import com.pabitero.booksdemo.service.BookService;
import com.pabitero.booksdemo.service.PublisherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Author will = new Author("William", "Pabitero");
        Author elon = new Author("Elon", "Musk");
        Author bill = new Author("Bill", "Gates");
        Author jeff = new Author("Jeff", "Bezos");
        authorService.saveAuthor(will);
        authorService.saveAuthor(elon);
        authorService.saveAuthor(bill);
        authorService.saveAuthor(jeff);

        Book book1 = new Book("How to Code", "1112", "Learn how to code in java using spring framework");
        Book book2 = new Book("Super Rocket Manual", "123423", "Guide to building a huge rocket");
        Book book3 = new Book("Microsoft: The Legacy", "3412", "History of Microsoft");
        bookService.saveBook(book1);
        bookService.saveBook(book2);
        bookService.saveBook(book3);

        Publisher pub1 = new Publisher("Pabs Publishing", "67-14 41st Ave", "Woodside", "NY", "11377");
        Publisher pub2 = new Publisher("NY Publishing", "41st W", "New York", "NY", "11153");
        publisherService.savePublisher(pub1);
        publisherService.savePublisher(pub2);

        will.getBooks().add(book1);
        book1.getAuthors().add(will);
        will.getBooks().add(book2);
        book2.getAuthors().add(will);
        will.getBooks().add(book3);
        book3.getAuthors().add(will);

        elon.getBooks().add(book2);
        book2.getAuthors().add(elon);
        elon.getBooks().add(book1);
        book1.getAuthors().add(elon);

        bill.getBooks().add(book3);
        book3.getAuthors().add(bill);

        jeff.getBooks().add(book2);
        book2.getAuthors().add(jeff);

        book1.setPublisher(pub1);
        book2.setPublisher(pub2);
        book3.setPublisher(pub2);
        pub1.getBooks().add(book1);
        pub2.getBooks().add(book2);
        pub2.getBooks().add(book3);

        bookService.saveBook(book1);
        bookService.saveBook(book2);
        bookService.saveBook(book3);

        authorService.saveAuthor(will);
        authorService.saveAuthor(elon);
        authorService.saveAuthor(bill);
        authorService.saveAuthor(jeff);

        publisherService.savePublisher(pub1);
        publisherService.savePublisher(pub2);

        System.out.println("Started in bootstrap");
        System.out.println("Number of books " + bookService.getBooks().size());
        System.out.println("Publisher Count " + publisherService.getPublishers().size());
        System.out.println("Publisher 1 Number of Books " + pub1.getBooks().size());
        bookService.getBooks().forEach(book -> System.out.println(book.getAuthors()));
        authorService.getAuthors().forEach(author -> System.out.println(author.getBooks()));

    }
}
