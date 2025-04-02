package com.example.emt2025main.service.application;

import com.example.emt2025main.dto.DisplayBookDto;
import com.example.emt2025main.dto.WishListDto;

import java.util.List;
import java.util.Optional;

public interface WishListApplicationService {

    List<DisplayBookDto> listAllBooksInWishList(Long id);

    Optional<WishListDto> getActiveWishList(String username);

    Optional<WishListDto> addBookToWishList(String username, Long bookId);

    void rentAll(String username);
}
