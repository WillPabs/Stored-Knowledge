package com.pabitero.booksdemo.repository;

import com.pabitero.booksdemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByFirstName(String firstName);
    Author findByLastName(String lastName);
}
