package com.booleanuk.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booleanuk.api.dto.BookRequest;
import com.booleanuk.api.model.Author;
import com.booleanuk.api.model.Book;
import com.booleanuk.api.model.Publisher;
import com.booleanuk.api.repository.AuthorRepository;
import com.booleanuk.api.repository.BookRepository;
import com.booleanuk.api.repository.PublisherRepository;

@Service 
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getOne(int id) {
        return bookRepository.findById(id);
    }

    public Book create(BookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setGenre(request.getGenre());
        book.setAuthor(author);
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    public Optional<Book> update(Integer id, BookRequest replacement) {
        Author author = authorRepository.findById(replacement.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        Publisher publisher = publisherRepository.findById(replacement.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        return this.bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(replacement.getTitle());
                    book.setGenre(replacement.getGenre());
                    book.setAuthor(author);
                    book.setPublisher(publisher);
                    return this.bookRepository.save(book);
                });
    }

    public Optional<Book> delete(Integer id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isPresent()) {
            this.bookRepository.delete(book.get());
        }
        return book;
    }
}