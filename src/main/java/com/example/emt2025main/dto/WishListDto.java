package com.example.emt2025main.dto;

import com.example.emt2025main.model.domain.WishList;

import java.util.List;

public record WishListDto(Long id, DisplayUserDto user, List<DisplayBookDto> books) {

    public static WishListDto from(WishList wishList) {
        return new WishListDto(
                wishList.getId(),
                DisplayUserDto.from(wishList.getUser()),
                DisplayBookDto.from(wishList.getBookList())
        );
    }

}
