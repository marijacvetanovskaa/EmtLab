package com.example.emt2025main.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Condition condition;

    private boolean rented;

    public BookCopy() {
        rented = true;
        condition = Condition.FAIR;
    }
}
