package com.example.emt2025main.web;

import com.example.emt2025main.dto.CreateAuthorDto;
import com.example.emt2025main.dto.DisplayAuthorDto;
import com.example.emt2025main.service.application.AuthorApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors of books")
public class AuthorController {
    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }


    @Operation(summary = "Get all authors", description = "Retrieves a list of all available authors.")
    @GetMapping
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.listAll();
    }

    @Operation(summary = "Get author by ID", description = "Finds an author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add an new author", description = "Creates an new author.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.create(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing author", description = "Updates an author by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> update(
            @PathVariable Long id,
            @RequestBody CreateAuthorDto createAuthorDto
    ) {
        return authorApplicationService.update(id, createAuthorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an author", description = "Deletes an author by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
