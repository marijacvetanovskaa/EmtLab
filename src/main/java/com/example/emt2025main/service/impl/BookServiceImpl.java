package com.example.emt2025main.service.impl;

import com.example.emt2025main.model.Author;
import com.example.emt2025main.model.Book;
import com.example.emt2025main.model.Category;
import com.example.emt2025main.model.Country;
import com.example.emt2025main.model.exceptions.InvalidBookIdException;
import com.example.emt2025main.repository.BookRepository;
import com.example.emt2025main.service.AuthorService;
import com.example.emt2025main.service.BookService;
import com.example.emt2025main.service.CountyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.emt2025main.service.FieldFilterSpecification.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CountyService countyService;

    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, CountyService countyService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.countyService = countyService;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public Book create(String name, Category category, Long countryId, Integer availableCopies, Long authorId) {
        Country country = this.countyService.findById(countryId);
        Author author= this.authorService.findById(authorId);
        return this.bookRepository.save(new Book(name, category,country, availableCopies,author));
    }

    @Override
    public Book update(Long id,String name, Category category, Long countryId, Integer availableCopies, Long authorId) {
        Country country = this.countyService.findById(countryId);
        Book book = this.findById(id);
        Author author= this.authorService.findById(authorId);

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setCountry(country);
        book.setAvailableCopies(availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book delete(Long id) {
        Book book = this.findById(id);
        this.bookRepository.delete(book);
        return book;
    }

    @Override
    public boolean rented(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        if(book.getAvailableCopies() == 0){
            return false;
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        this.bookRepository.save(book);
        return true;
    }

    @Override
    public Page<Book> findPage(String name, Category category, Long countryId,Long authorId, int pageNum, int pageSize) {
        Specification<Book> specification = Specification
                .where(filterContainsText(Book.class, "name", name))
                .and(filterEqualsV(Book.class, "category", category))
                .and(filterEquals(Book.class, "countryId", countryId))
                .and(filterEquals(Book.class, "authorId", authorId));

        return this.bookRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize)
        );
    }
}
