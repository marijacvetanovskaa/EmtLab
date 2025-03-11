package com.example.emt2025main.repository;

import com.example.emt2025main.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
