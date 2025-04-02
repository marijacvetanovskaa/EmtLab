package com.example.emt2025main.service.application;

import com.example.emt2025main.dto.CreateBookDto;
import com.example.emt2025main.dto.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {

    Optional<DisplayBookDto> findById(Long id);
    Optional<DisplayBookDto> update(Long id, CreateBookDto dto);
    Optional<DisplayBookDto> create(CreateBookDto dto);
    List<DisplayBookDto> listAll();
    void deleteById(Long id);
    void addCopy(Long id);
    Optional<DisplayBookDto> rent(Long id);
}
