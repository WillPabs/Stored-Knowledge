package com.pabitero.booksdemo.repository;

import com.pabitero.booksdemo.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByName(String name);
}
