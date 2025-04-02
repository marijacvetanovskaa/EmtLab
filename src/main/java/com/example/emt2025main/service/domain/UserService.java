package com.example.emt2025main.service.domain;

import com.example.emt2025main.model.domain.Role;
import com.example.emt2025main.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);
}

