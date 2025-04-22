package com.example.emt2025main.repository;

import com.example.emt2025main.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = {"username", "name", "surname", "password", "role"})
    Optional<User> findByUsernameAndPassword(String username, String password);

    @EntityGraph(attributePaths = {"username", "name", "surname", "password", "role"})
    Optional<User> findByUsername(String username);
}

