package com.pabitero.booksdemo.service;

import com.pabitero.booksdemo.entity.Publisher;
import com.pabitero.booksdemo.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> savePublishers(List<Publisher> publishers) {
        return publisherRepository.saveAll(publishers);
    }

    public List<Publisher> getPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    public Publisher getPublisherByName(String name) {
        return publisherRepository.findByName(name);
    }

    public String deletePublisher(Long id) {
        publisherRepository.deleteById(id);
        return "Publisher #: " + id + " removed";
    }

    public Publisher updatePublisher(Publisher publisher) {
        Publisher existingPublisher = publisherRepository.findById(publisher.getId()).orElse(publisher);
        existingPublisher.setName(publisher.getName());
        existingPublisher.setAddressLine1(publisher.getAddressLine1());
        existingPublisher.setCity(publisher.getCity());
        existingPublisher.setState(publisher.getState());
        existingPublisher.setZip(publisher.getZip());
        existingPublisher.setBooks(publisher.getBooks());
        return publisherRepository.save(existingPublisher);
    }
}
