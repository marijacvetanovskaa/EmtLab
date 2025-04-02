package com.example.emt2025main.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
//http://localhost:8080/swagger-ui/index.html
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Author author;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Country country;

    @OneToMany
    private List<BookCopy> bookCopies;

    public Book(String name, Category category, Country country, Author author) {
        this.name = name;
        this.category = category;
        this.country = country;
        this.author=author;
        this.bookCopies = new ArrayList<>();
    }

    public Book() {
    }
}
