package com.example.emt2025main.config;

import com.example.emt2025main.model.domain.*;
import com.example.emt2025main.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final CountyRepository countyRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final WishListRepository wishListRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, CountyRepository countyRepository, AuthorRepository authorRepository, BookRepository bookRepository, WishListRepository wishListRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.countyRepository = countyRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.wishListRepository = wishListRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void init() {
        Country country = countyRepository.save(new Country("Macedonia", "Europe"));
        Author author = authorRepository.save(new Author("Krste", "Petkov Misirkov", country));
        Book book = bookRepository.save(new Book("On the macedonian matters", Category.HISTORY, country, author));

        User admin = userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "Marija",
                "Cvetanovska",
                Role.ROLE_LIBRARIAN
        ));
        WishList wishList = wishListRepository.save(new WishList(admin));


        User user = userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "Test",
                "User",
                Role.ROLE_USER
        ));
        WishList wishList1 = wishListRepository.save(new WishList(user));

    }
}
