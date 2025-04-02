package com.example.emt2025main.service.application.impl;

import com.example.emt2025main.dto.DisplayBookDto;
import com.example.emt2025main.dto.WishListDto;
import com.example.emt2025main.service.application.WishListApplicationService;
import com.example.emt2025main.service.domain.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListApplicationServiceImpl implements WishListApplicationService {

    private WishListService wishListService;

    public WishListApplicationServiceImpl(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @Override
    public List<DisplayBookDto> listAllBooksInWishList(Long id) {
        return wishListService.listAllBooksInWishList(id).stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public Optional<WishListDto> getActiveWishList(String username) {
        return wishListService.getActiveWishList(username).map(WishListDto::from);
    }

    @Override
    public Optional<WishListDto> addBookToWishList(String username, Long bookId) {
        return wishListService.addBookToWishList(username, bookId).map(WishListDto::from);
    }

    @Override
    public void rentAll(String username) {
        wishListService.rentAll(username);
    }
}
