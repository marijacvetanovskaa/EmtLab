package com.example.emt2025main.service.domain.impl;

import com.example.emt2025main.model.domain.Country;
import com.example.emt2025main.repository.CountyRepository;
import com.example.emt2025main.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountyServiceImpl implements CountryService {

    private final CountyRepository countyRepository;

    public CountyServiceImpl(CountyRepository hotelRepository) {
        this.countyRepository = hotelRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countyRepository.findById(id);
    }

    @Override
    public List<Country> listAll() {
        return this.countyRepository.findAll();
    }

    @Override
    public Optional<Country> create(Country country) {
        return Optional.of(this.countyRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countyRepository.findById(id).map(existingCountry -> {
            if (country.getName() != null) {
                existingCountry.setName(country.getName());
            }
            if (country.getContinent() != null) {
                existingCountry.setContinent(country.getContinent());
            }
            return countyRepository.save(existingCountry);
        });
    }

    @Override
    public void delete(Long id) {
        this.countyRepository.deleteById(id);
    }
}
