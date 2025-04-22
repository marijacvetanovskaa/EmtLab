package com.example.emt2025main.dto;

import com.example.emt2025main.model.views.BooksByAuthorView;

import java.util.List;

public record DisplayBooksByAuthorDto(Long id, Long bookCount) {

    public static DisplayBooksByAuthorDto from(BooksByAuthorView booksByAuthorView) {
        return new DisplayBooksByAuthorDto(booksByAuthorView.getAuthorId(), booksByAuthorView.getBookCount());
    }

    public static List<DisplayBooksByAuthorDto> from(List<BooksByAuthorView> booksByAuthorViewList) {
        return booksByAuthorViewList.stream().map(DisplayBooksByAuthorDto::from).toList();
    }
}
