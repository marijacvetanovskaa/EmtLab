package com.example.emt2025main.service.application.impl;

import com.example.emt2025main.dto.CreateCountryDto;
import com.example.emt2025main.dto.DisplayCountryDto;
import com.example.emt2025main.service.domain.CountryService;
import com.example.emt2025main.service.application.CountryApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> create(CreateCountryDto dto) {
        return countryService.create(dto.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto dto) {
        return countryService.update(id, dto.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public List<DisplayCountryDto> listAll() {
        return countryService.listAll().stream().map(DisplayCountryDto::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        countryService.delete(id);
    }
}
