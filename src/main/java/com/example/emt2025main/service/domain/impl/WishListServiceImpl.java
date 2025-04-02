package com.example.emt2025main.service.domain.impl;

import com.example.emt2025main.model.domain.Book;
import com.example.emt2025main.model.domain.User;
import com.example.emt2025main.model.domain.WishList;
import com.example.emt2025main.repository.UserRepository;
import com.example.emt2025main.repository.WishListRepository;
import com.example.emt2025main.service.domain.BookService;
import com.example.emt2025main.service.domain.WishListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public WishListServiceImpl(WishListRepository wishListRepository, UserRepository userRepository, BookService bookService) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInWishList(Long id) {
        return wishListRepository.findById(id).orElseThrow(RuntimeException::new).getBookList();
    }

    @Override
    public Optional<WishList> getActiveWishList(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        return wishListRepository.findByUser(user);
    }

    @Override
    public Optional<WishList> addBookToWishList(String username, Long bookId) {
        Book book = bookService.findById(bookId).orElseThrow(RuntimeException::new);
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        WishList wishList = wishListRepository.findByUser(user).orElseThrow(RuntimeException::new);
        wishList.getBookList().add(book);
        return Optional.of(wishListRepository.save(wishList));
    }

    @Override
    public void rentAll(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        WishList wishList = wishListRepository.findByUser(user).orElseThrow(RuntimeException::new);
        wishList.getBookList().forEach(book -> bookService.rent(book.getId()));
        wishList.setBookList(new ArrayList<>());
        wishListRepository.save(wishList);
    }
}
