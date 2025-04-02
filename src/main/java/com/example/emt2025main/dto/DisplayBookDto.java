package com.example.emt2025main.dto;

import com.example.emt2025main.model.domain.Author;
import com.example.emt2025main.model.domain.Book;
import com.example.emt2025main.model.domain.Category;
import com.example.emt2025main.model.domain.Country;

import java.util.List;

public record DisplayBookDto(Long id, String name, Long country, Category category, Long author) {

    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(book.getId(), book.getName(), book.getCountry().getId(), book.getCategory(), book.getAuthor().getId());
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).toList();
    }

    public Book toBook(Country country, Author author) {
        return new Book(name, category, country, author);
    }
}
