package com.example.emt2025main.repository;

import com.example.emt2025main.model.domain.User;
import com.example.emt2025main.model.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    Optional<WishList> findByUser(User user);
}
