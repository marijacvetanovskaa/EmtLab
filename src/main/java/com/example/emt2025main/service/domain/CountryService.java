package com.example.emt2025main.service.domain;

import com.example.emt2025main.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);
    List<Country> listAll();
    Optional<Country> create(Country country);
    Optional<Country> update(Long id, Country country);
    void delete(Long id);
}
