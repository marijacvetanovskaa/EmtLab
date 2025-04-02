package com.example.emt2025main.service.domain.impl;

import com.example.emt2025main.model.domain.*;
import com.example.emt2025main.repository.BookRepository;
import com.example.emt2025main.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id).map(existingBook -> {
            if (book.getName() != null) {
                existingBook.setName(book.getName());
            }
            if (book.getCountry() != null) {
                existingBook.setCountry(book.getCountry());
            }
            if (book.getAuthor() != null) {
                existingBook.setAuthor(book.getAuthor());
            }
            if (book.getCategory() != null) {
                existingBook.setCountry(book.getCountry());
            }
            return existingBook;
        });
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> rent(Long id) {
        Book book = this.listAll().stream()
                .filter(book1 -> Objects.equals(book1.getId(), id)).toList().get(0);
        book.getBookCopies().remove(book.getBookCopies().size() - 1);
        update(id, book);
        return Optional.of(book);
    }

    @Override
    public void addBookCopy(Long id) {
        bookRepository.findById(id).map(existing -> {
            existing.getBookCopies().add(new BookCopy());
            update(id, existing);
            return existing;
        });
    }
}
