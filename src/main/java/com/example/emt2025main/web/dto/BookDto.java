package com.example.emt2025main.web.dto;

import com.example.emt2025main.model.Category;
import lombok.Getter;

public class BookDto {
    public String name;
    public Long authorId;
    public Category category;
    public Integer availableCopies;
    public Long countryId;

    public BookDto(String name, Long authorId, Category category, Integer availableCopies, Long countryId) {
        this.name = name;
        this.authorId = authorId;
        this.category = category;
        this.availableCopies = availableCopies;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public Long getCountryId() {
        return countryId;
    }
}
