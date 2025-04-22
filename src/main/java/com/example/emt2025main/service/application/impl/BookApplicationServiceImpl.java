package com.example.emt2025main.service.application.impl;

import com.example.emt2025main.dto.CreateBookDto;
import com.example.emt2025main.dto.DisplayBookDto;
import com.example.emt2025main.dto.DisplayBooksByAuthorDto;
import com.example.emt2025main.model.domain.Author;
import com.example.emt2025main.model.domain.Country;
import com.example.emt2025main.service.domain.AuthorService;
import com.example.emt2025main.service.domain.BookService;
import com.example.emt2025main.service.domain.CountryService;
import com.example.emt2025main.service.application.BookApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto dto) {
        Optional<Author> author = authorService.findById(dto.author());
        Optional<Country> country = countryService.findById(dto.country());
        return bookService.update(id, dto.toBook(country.orElse(null), author.orElse(null))).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> create(CreateBookDto dto) {
        Optional<Author> author = authorService.findById(dto.author());
        Optional<Country> country = countryService.findById(dto.country());
        return bookService.create(dto.toBook(country.orElse(null), author.orElse(null))).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> listAll() {
        return bookService.listAll().stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        bookService.delete(id);
    }

    @Override
    public void addCopy(Long id) {
        bookService.addBookCopy(id);
    }

    @Override
    public Optional<DisplayBookDto> rent(Long id) {
        return bookService.rent(id).map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBooksByAuthorDto> listBooksByAuthor() {
        return bookService.listAllBooksByAuthor().stream().map(DisplayBooksByAuthorDto::from).toList();
    }
}
