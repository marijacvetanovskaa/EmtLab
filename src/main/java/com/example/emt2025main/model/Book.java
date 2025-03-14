package com.example.emt2025main.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//http://localhost:8080/swagger-ui/index.html
public class Book {

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

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

    public Book(String name, Category category, Country country, Author author) {
        this.name = name;
        this.category = category;
        this.country = country;
        this.author=author;

    }
}
