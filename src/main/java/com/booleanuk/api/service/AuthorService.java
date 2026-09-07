package com.booleanuk.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booleanuk.api.model.Author;
import com.booleanuk.api.repository.AuthorRepository;

@Service 
public class AuthorService {
    private final AuthorRepository authorRepository;
    
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> getOne(int id) {
        return authorRepository.findById(id);
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> update(Integer id, Author replacement) {
        return this.authorRepository.findById(id)
                .map(author -> {
                    author.setFirstName(replacement.getFirstName());
                    author.setLastName(replacement.getLastName());
                    author.setEmail(replacement.getEmail());
                    author.setAlive(replacement.isAlive());
                    return this.authorRepository.save(author);
                });
    }

    public Optional<Author> delete(Integer id) {
        Optional<Author> author = this.authorRepository.findById(id);
        if (author.isPresent()) {
            this.authorRepository.delete(author.get());
        }
        return author;
    }
}