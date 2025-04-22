package com.example.emt2025main.listeners;

import com.example.emt2025main.events.AuthorEvent;
import com.example.emt2025main.service.domain.AuthorService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandler {

    private final AuthorService authorService;

    public AuthorEventHandler(AuthorService authorService) {
        this.authorService = authorService;
    }

    @EventListener
    public void onAuthorChange(AuthorEvent event) {
        this.authorService.refreshMaterializedView();
    }
}

