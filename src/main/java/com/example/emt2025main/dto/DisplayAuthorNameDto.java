package com.example.emt2025main.dto;

import com.example.emt2025main.model.projections.AuthorProjection;

public record DisplayAuthorNameDto(String name, String surname) {

    public static DisplayAuthorNameDto from(AuthorProjection authorProjection) {
        return new DisplayAuthorNameDto(authorProjection.getName(), authorProjection.getSurname());
    }
}
