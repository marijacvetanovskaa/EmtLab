package com.example.emt2025main.events;

import com.example.emt2025main.model.domain.Author;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorEvent extends ApplicationEvent {

    private LocalDateTime when;

    public AuthorEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public AuthorEvent(Author source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}

