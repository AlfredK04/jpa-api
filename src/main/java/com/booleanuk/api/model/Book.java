package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String genre;

    @ManyToOne 
    @JoinColumn (name = "author_id", nullable = false)
    @JsonIgnoreProperties(value = "books", allowSetters = true)
    @ToString.Exclude
    private Author author;

    @ManyToOne 
    @JoinColumn (name = "publisher_id", nullable = false)
    @JsonIgnoreProperties(value = "books", allowSetters = true)
    @ToString.Exclude
    private Publisher publisher;
}

