package com.example.emt2025main.service.application;

import com.example.emt2025main.dto.CreateCountryDto;
import com.example.emt2025main.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    Optional<DisplayCountryDto> findById(Long id);
    Optional<DisplayCountryDto> create(CreateCountryDto dto);
    Optional<DisplayCountryDto> update(Long id, CreateCountryDto dto);
    List<DisplayCountryDto> listAll();
    void deleteById(Long id);
}
