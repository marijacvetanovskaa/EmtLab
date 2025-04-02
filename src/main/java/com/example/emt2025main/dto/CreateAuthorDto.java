package com.example.emt2025main.dto;

import com.example.emt2025main.model.domain.Author;
import com.example.emt2025main.model.domain.Country;

public record CreateAuthorDto (String name, String surname, Long country) {

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
