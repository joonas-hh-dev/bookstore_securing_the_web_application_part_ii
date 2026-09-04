package com.example.bookstore.web;

import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.domain.Book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @GetMapping("/addbook")
    public String showAddBookForm() {
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(
        @RequestParam String title,
        @RequestParam String author,
        @RequestParam int publicationYear,
        @RequestParam String isbn,
        @RequestParam float price
    ) {
        Book book = new Book(title, author, publicationYear, isbn, price);
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
}
    
    
}
