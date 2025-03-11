package com.example.emt2025main.web;

import com.example.emt2025main.model.Author;
import com.example.emt2025main.service.AuthorService;
import com.example.emt2025main.web.dto.AuthorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAll(){
        return this.authorService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id){
        Author author = this.authorService.findById(id);
        if (author != null) {
            return ResponseEntity.ok(author);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addNewAuthor(@RequestBody AuthorDto authorDto){
        Author author = this.authorService.create(authorDto.name, authorDto.surname, authorDto.countryId);

        if (author != null) {
            return ResponseEntity.ok(author);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        this.authorService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
