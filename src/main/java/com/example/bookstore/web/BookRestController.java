package com.example.bookstore.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.domain.Book;

@RestController
public class BookRestController {

    private BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Show all books
    @GetMapping("/books")
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    // Find book by ID
    @GetMapping("/books/{id}")
    public Optional<Book> findById(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }
}
