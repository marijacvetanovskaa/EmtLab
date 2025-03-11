package com.example.emt2025main.service.impl;

import com.example.emt2025main.model.Author;
import com.example.emt2025main.model.exceptions.InvalidAuthorIdException;
import com.example.emt2025main.repository.AuthorRepository;
import com.example.emt2025main.service.AuthorService;
import com.example.emt2025main.service.CountyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountyService countyService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountyService countyService) {
        this.authorRepository = authorRepository;
        this.countyService = countyService;
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        return this.authorRepository.save(new Author(name, surname, countyService.findById(countryId)));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
