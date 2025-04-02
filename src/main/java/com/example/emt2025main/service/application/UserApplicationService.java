package com.example.emt2025main.service.application;

import com.example.emt2025main.dto.CreateUserDto;
import com.example.emt2025main.dto.DisplayUserDto;
import com.example.emt2025main.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}

