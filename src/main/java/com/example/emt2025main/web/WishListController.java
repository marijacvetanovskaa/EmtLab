package com.example.emt2025main.web;

import com.example.emt2025main.dto.WishListDto;
import com.example.emt2025main.model.domain.User;
import com.example.emt2025main.service.application.WishListApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wish-list")
@Tag(name = "Wishlist API", description = "Endpoints for managing the wishlist")
public class WishListController {

    private final WishListApplicationService wishListApplicationService;

    public WishListController(WishListApplicationService wishListApplicationService) {
        this.wishListApplicationService = wishListApplicationService;
    }

    @Operation(
            summary = "Get active wishlist",
            description = "Retrieves the active wishlist for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Wishlist retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "Wishlist not found")}
    )
    @GetMapping
    public ResponseEntity<WishListDto> getActiveWishList(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return wishListApplicationService.getActiveWishList(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add book to wishlist",
            description = "Adds a book to the wishlist for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Book added to wishlist successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Book not found")}
    )
    @PostMapping("/add-book/{id}")
    public ResponseEntity<WishListDto> addBookToWishList(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return wishListApplicationService.addBookToWishList(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}

