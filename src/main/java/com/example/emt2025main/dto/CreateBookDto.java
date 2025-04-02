package com.example.emt2025main.dto;

import com.example.emt2025main.model.domain.Author;
import com.example.emt2025main.model.domain.Book;
import com.example.emt2025main.model.domain.Category;
import com.example.emt2025main.model.domain.Country;

import java.util.List;

public record CreateBookDto(String name, Category category, Long country, Long author) {

    public Book toBook(Country country, Author author) {
        return new Book(name, category, country, author);
    }

    public static CreateBookDto from(Book book) {
        return new CreateBookDto(book.getName(), book.getCategory(), book.getCountry().getId(), book.getAuthor().getId());
    }

    public static List<CreateBookDto> from(List<Book> books) {
        return books.stream().map(CreateBookDto::from).toList();
    }
}
