package com.example.BookApplication.service;

import com.example.BookApplication.entity.Book;
import com.example.BookApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book addBook(Book book){
        boolean isTitleValid = validateBookTitle(book.getTitle());
        if(isTitleValid) {
            return bookRepository.save(book);
        }
        else
            throw new RuntimeException("Invalid Book Title is Passed");
    }

    public Book getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }


    public Book updateBook(Book book) {
        if(getBookById(Long.valueOf(1))!=null) {
            return addBook(book);
        }
        return addBook(book);
    }

    public void deleteBookById(Long id) {
        if(getBookById(id).isPresent()) {
            bookRepository.deleteById(id);
        }
    }

    private boolean validateBookTitle(String title) {
        return title != null && !title.isEmpty();
    }
}
