package com.example.emt2025main.service;

import com.example.emt2025main.model.Author;
import com.example.emt2025main.model.Country;

import java.util.List;

public interface AuthorService {
    Author findById(Long id);
    List<Author> listAll();
    Author create(String name, String surname, Long countryId);
    void deleteById(Long id);
}
