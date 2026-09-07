package com.booleanuk.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booleanuk.api.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
