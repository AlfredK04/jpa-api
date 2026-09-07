package com.booleanuk.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.booleanuk.api.model.Publisher;
import com.booleanuk.api.service.PublisherService;

@RestController
@RequestMapping("publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping 
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable int id) {
        Optional<Publisher> optPublisher = publisherService.getOne(id);
        if (optPublisher.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(optPublisher.get());
    }

    @PostMapping 
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher) {
        Publisher newPublisher = publisherService.create(publisher);
        return new ResponseEntity<>(newPublisher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(
            @PathVariable Integer id,
            @RequestBody Publisher publisher) {
        Publisher updated = this.publisherService.update(id, publisher)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable Integer id) {
        Publisher deleted = this.publisherService.delete(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return ResponseEntity.ok(deleted);
    }
}