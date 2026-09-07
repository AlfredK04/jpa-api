package com.booleanuk.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booleanuk.api.model.Publisher;
import com.booleanuk.api.repository.PublisherRepository;

@Service 
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getOne(int id) {
        return publisherRepository.findById(id);
    }

    public Publisher create(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Optional<Publisher> update(Integer id, Publisher replacement) {
        return this.publisherRepository.findById(id)
                .map(publisher -> {
                    publisher.setName(replacement.getName());
                    publisher.setLocation(replacement.getLocation());
                    return this.publisherRepository.save(publisher);
                });
    }

    public Optional<Publisher> delete(Integer id) {
        Optional<Publisher> publisher = this.publisherRepository.findById(id);
        if (publisher.isPresent()) {
            this.publisherRepository.delete(publisher.get());
        }
        return publisher;
    }
}

