package com.example.emt2025main.web;

import com.example.emt2025main.model.Book;
import com.example.emt2025main.model.Category;
import com.example.emt2025main.service.BookService;
import com.example.emt2025main.web.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/","/books"})
    public List<Book> getAll(){
        return this.bookService.listAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);

        if (book != null) {
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> addNewBook(@RequestBody BookDto bookDto) {
        Book book = this.bookService.create(
                bookDto.getName(), bookDto.getCategory(), bookDto.getCountryId(), bookDto.getAvailableCopies(), bookDto.getAuthorId());

        if (book != null) {
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/books/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto){
        Book book = this.bookService.update(id, bookDto.getName(), bookDto.getCategory(), bookDto.getCountryId(), bookDto.getAvailableCopies(), bookDto.getAuthorId());

        if (book != null) {
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/books/rent/{id}")
    public ResponseEntity<Void> markTaken(@PathVariable Long id){
        boolean isOK = this.bookService.rented(id);
        if(isOK){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        List<Category> list = new ArrayList<>();
        Collections.addAll(list, Category.values());
        return list;
    }
}
