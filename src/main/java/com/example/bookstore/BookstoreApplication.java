package com.example.bookstore;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;
import com.example.bookstore.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreRunner(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return args -> {
			Category fiction = new Category("Kaunokirjallisuus", new ArrayList<>());
			Category nonFiction = new Category("Tietokirjallisuus", new ArrayList<>());
			Category biographies = new Category("Elämänkerrat", new ArrayList<>());

			categoryRepository.save(fiction);
			categoryRepository.save(nonFiction);
			categoryRepository.save(biographies);

			Book book = new Book("Esimerkki", "Mikko Mallikas", 2026, "123456", 20.0f, fiction);
			Book book2 = new Book("Toinen esimerkki", "Matti Mallikas", 2025, "654321", 10.0f, nonFiction);
			bookRepository.save(book);
			bookRepository.save(book2);

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			User user = new User("mattimeikalainen", passwordEncoder.encode("SalainenSalailija123"), "matti.meikalainen@esimerkki.com", "ADMIN");
			User user2 = new User("maijameikalainen", passwordEncoder.encode("SalaPoliisi345"), "maija.meikalainen@esimerkki.com", "USER");

			userRepository.save(user);
			userRepository.save(user2);
		};
	}

}
