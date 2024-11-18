package com.example.BookApplication.repository;

import com.example.BookApplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookByTitle(String title);
}
