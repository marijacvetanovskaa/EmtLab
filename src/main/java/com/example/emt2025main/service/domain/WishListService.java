package com.example.emt2025main.service.domain;

import com.example.emt2025main.model.domain.Book;
import com.example.emt2025main.model.domain.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListService {
    List<Book> listAllBooksInWishList(Long id);

    Optional<WishList> getActiveWishList(String username);

    Optional<WishList> addBookToWishList(String username, Long bookId);

    void rentAll(String username);

}
