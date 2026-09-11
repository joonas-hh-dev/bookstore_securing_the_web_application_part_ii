package com.example.bookstore.web;

import com.example.bookstore.repository.CategoryRepository;
import java.util.Optional;

import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookController {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }
    
    @GetMapping("/booklist")
    public String showBooks(Model model) {

        model.addAttribute("books", bookRepository.findAll());

        return "booklist";
    }

    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String addBook(
        @RequestParam String title,
        @RequestParam String author,
        @RequestParam int publicationYear,
        @RequestParam String isbn,
        @RequestParam float price,
        @RequestParam Long category
    ) {
        Optional<Category> result = categoryRepository.findById(category);
        Category selectedCategory = result.get();
        Book book = new Book(title, author, publicationYear, isbn, price, selectedCategory);
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Optional<Book> result = bookRepository.findById(id);
        Book book = result.get();
        model.addAttribute("book", book);
        return "editbook";
    }
    
    @PostMapping("/savebook/{id}")
    public String saveBook(
        @PathVariable Long id,
        @RequestParam String title,
        @RequestParam String author,
        @RequestParam int publicationYear,
        @RequestParam String isbn,
        @RequestParam float price
    ) {
        Optional<Book> result = bookRepository.findById(id);
        Book book = result.get();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
        book.setIsbn(isbn);
        book.setPrice(price);
        bookRepository.save(book);
    return "redirect:/booklist";
    }
}
