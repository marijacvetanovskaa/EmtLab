package com.example.emt2025main.dto;

import com.example.emt2025main.model.views.AuthorsByCountryView;

import java.util.List;

public record DisplayAuthorsByCountryDto(Long countryId, Long authorId) {

    public static DisplayAuthorsByCountryDto from(AuthorsByCountryView authorsByCountryView) {
        return new DisplayAuthorsByCountryDto(
                authorsByCountryView.getCountryId(), authorsByCountryView.getAuthorCount());
    }

    public static List<DisplayAuthorsByCountryDto> from(List<AuthorsByCountryView> authorsByCountryViewList) {
        return authorsByCountryViewList.stream().map(DisplayAuthorsByCountryDto::from).toList();
    }
}
