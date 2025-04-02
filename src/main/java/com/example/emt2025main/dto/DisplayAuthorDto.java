package com.example.emt2025main.dto;

import com.example.emt2025main.model.domain.Author;
import com.example.emt2025main.model.domain.Country;

import java.util.List;

public record DisplayAuthorDto(Long id, String name, String surname, Long country) {

    public static DisplayAuthorDto from(Author author) {
        return new DisplayAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry().getId());
    }

    public static List<DisplayAuthorDto> from(List<Author> authors) {
        return authors.stream().map(DisplayAuthorDto::from).toList();
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
