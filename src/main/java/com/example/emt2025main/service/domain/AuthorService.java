package com.example.emt2025main.service.domain;

import com.example.emt2025main.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
    List<Author> listAll();
    Optional<Author> create(Author author);
    Optional<Author> update(Long id, Author author);
    void deleteById(Long id);
}
