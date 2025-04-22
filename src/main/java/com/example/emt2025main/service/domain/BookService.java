package com.example.emt2025main.service.domain;

import com.example.emt2025main.model.domain.Book;
import com.example.emt2025main.model.views.BooksByAuthorView;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();
    Optional<Book> findById(Long id);
    Optional<Book> create(Book book);
    Optional<Book> update(Long id, Book book);
    void delete(Long id);
    Optional<Book> rent(Long id);
    void addBookCopy(Long id);
    List<BooksByAuthorView> listAllBooksByAuthor();
    void refreshMaterializedView();
}
