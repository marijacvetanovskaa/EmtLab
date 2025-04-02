package com.example.emt2025main.service.application.impl;

import com.example.emt2025main.dto.CreateAuthorDto;
import com.example.emt2025main.dto.DisplayAuthorDto;
import com.example.emt2025main.model.domain.Country;
import com.example.emt2025main.service.domain.AuthorService;
import com.example.emt2025main.service.domain.CountryService;
import com.example.emt2025main.service.application.AuthorApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> create(CreateAuthorDto dto) {
        Optional<Country> country = countryService.findById(dto.country());
        return authorService.create(dto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto dto) {
        Optional<Country> country = countryService.findById(dto.country());
        return authorService.update(id, dto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::from);
    }

    @Override
    public List<DisplayAuthorDto> listAll() {
        return authorService.listAll().stream().map(DisplayAuthorDto::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }
}
