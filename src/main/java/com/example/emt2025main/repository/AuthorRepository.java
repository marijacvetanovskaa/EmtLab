package com.example.emt2025main.repository;

import com.example.emt2025main.model.domain.Author;
import com.example.emt2025main.model.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<AuthorProjection> findAllBy();
}
