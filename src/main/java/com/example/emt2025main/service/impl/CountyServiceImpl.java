package com.example.emt2025main.service.impl;

import com.example.emt2025main.model.Country;
import com.example.emt2025main.model.exceptions.InvalidCountryIdException;
import com.example.emt2025main.repository.CountyRepository;
import com.example.emt2025main.service.CountyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyServiceImpl implements CountyService {

    private final CountyRepository countyRepository;

    public CountyServiceImpl(CountyRepository hotelRepository) {
        this.countyRepository = hotelRepository;
    }

    @Override
    public Country findById(Long id) {
        return this.countyRepository.findById(id).orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public List<Country> listAll() {
        return this.countyRepository.findAll();
    }

    @Override
    public Country create(String name, String continent) {
        return this.countyRepository.save(new Country(name, continent));
    }

    @Override
    public void delete(Long id) {
        this.countyRepository.deleteById(id);
    }
}
