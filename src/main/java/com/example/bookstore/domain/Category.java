package com.example.bookstore.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;
     private String name;
     @JsonIgnore
     @OneToMany (mappedBy = "category")
     private List<Book> books;

     public Category(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Category() {
    }
    
    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

     public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
