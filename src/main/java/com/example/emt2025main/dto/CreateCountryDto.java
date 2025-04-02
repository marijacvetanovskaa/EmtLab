package com.example.emt2025main.dto;

import com.example.emt2025main.model.domain.Country;

public record CreateCountryDto(String name, String continent) {

    public Country toCountry() {
        return new Country(name, continent);
    }
}
