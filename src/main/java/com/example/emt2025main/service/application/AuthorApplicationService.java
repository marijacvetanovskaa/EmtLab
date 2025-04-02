package com.example.emt2025main.service.application;

import com.example.emt2025main.dto.CreateAuthorDto;
import com.example.emt2025main.dto.DisplayAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    Optional<DisplayAuthorDto> findById(Long id);
    Optional<DisplayAuthorDto> create(CreateAuthorDto dto);
    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto dto);
    List<DisplayAuthorDto> listAll();
    void deleteById(Long id);
}
