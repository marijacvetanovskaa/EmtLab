package com.example.emt2025main.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Book> bookList = new ArrayList<>();

    public WishList(){

    }
    public WishList(User user) {
        this.user = user;
        this.bookList = new ArrayList<>();
    }
}
