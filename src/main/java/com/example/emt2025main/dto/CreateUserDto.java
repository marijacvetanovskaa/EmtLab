package com.example.emt2025main.dto;

import com.example.emt2025main.model.domain.Role;
import com.example.emt2025main.model.domain.User;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    /*
        todo: add repeat password logic
     */
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}

