package com.example.bookstore.web;

import com.example.bookstore.repository.BookRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @GetMapping("/booklist")
    public String showBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll());

        return "booklist";
    }
    
}
