package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.Category;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Autowired 
	private CategoryRepository categoryRepository;

	@Test 
	public void createBook() {
		Category children = new Category("Lastenkirjat", new ArrayList<>());
		categoryRepository.save(children);
		Book book = new Book("Kolmas esimerkki", "Maija Mallikas", 2024, "987654", 30.0f, children);
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void deleteBook() {
		Category youngAdult = new Category("Nuoret aikuiset", new ArrayList<>());
		categoryRepository.save(youngAdult);
		Book book = new Book("Neljäs esimerkki", "Milla Mallikas", 2023, "789456", 35.0f, youngAdult);
		bookRepository.save(book);
		bookRepository.deleteById(book.getId());
		assertThat(bookRepository.existsById(book.getId())).isFalse();
	}

	@Test
	public void searchBook() {
		Category youngAdult = new Category("Nuoret aikuiset", new ArrayList<>());
		categoryRepository.save(youngAdult);
		Book book = new Book("Neljäs esimerkki", "Milla Mallikas", 2023, "789456", 35.0f, youngAdult);
		bookRepository.save(book);
		Optional<Book> result = bookRepository.findById(book.getId());
		assertThat(result).isPresent();
		assertThat(result.get().getId()).isEqualTo(book.getId());
	}
}