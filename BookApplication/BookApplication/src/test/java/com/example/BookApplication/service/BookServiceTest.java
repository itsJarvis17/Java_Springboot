package com.example.BookApplication.service;

import com.example.BookApplication.entity.Book;
import com.example.BookApplication.repository.BookRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    BookService bookService;
    private static Book book = null;

    @BeforeAll
    static void init() {
        book = new Book();
        book.setId(1);
        book.setTitle("Wings of Fire");
        book.setTitle("A.P.J Abdul Kalam");
        book.setGenre("Science");
    }

    @BeforeEach
    void beforeEachTest() {
        System.out.println("BeforeEach");
    }

    @Test
    @DisplayName("Test should be passed on saving book in database")
    void testBookIsSuccessfullySaved() {
        System.out.println("addBook");
        // Data preparation

        // Mocking calls if any
        when(bookRepository.save(book)).thenReturn(book);

        // Calling actual methods
        Book addedBook = bookService.addBook(book);

        // Assertions
        assertEquals(addedBook.getId(),book.getId());
    }

    @Test
    @DisplayName("Test to verify deleting book functionality")
    void testBookWithValidIdIsDeletedSuccessfully() {
        // Mocking calls if any
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).deleteById(1L);
        // Call actual method
        bookService.deleteBookById(1L);

        //Assertions
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test positive private methods")
    void testPrivateMethod_ValidateBookNameIfValid() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateBookTitle = BookService.class.getDeclaredMethod("validateBookTitle", String.class);
        validateBookTitle.setAccessible(true);
        Boolean isTitleValid = (Boolean) validateBookTitle.invoke(bookService, "Harry Potter");
        assertTrue(isTitleValid);
    }

    @Test
    @DisplayName("Test negative private methods")
    void testPrivateMethod_ValidateBookNameIfInvalid() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method validateBookTitle = BookService.class.getDeclaredMethod("validateBookTitle", String.class);
        validateBookTitle.setAccessible(true);
        Boolean isTitleValid = (Boolean) validateBookTitle.invoke(bookService, "");
        assertFalse(isTitleValid);
    }

    @Test
    @DisplayName("Test methods throwing exception")
    void testAddBookShouldThrowExceptionForInvalidName() {
        book.setTitle("");
        RuntimeException runtimeException = assertThrows(RuntimeException.class, ()->bookService.addBook(book));
        assertEquals("Invalid Book Title is Passed",runtimeException.getMessage());
        //verify(bookRepository, never()).save(book);
        verify(bookRepository, never()).save(any(Book.class));
    }
    @AfterEach
    void afterEachTest() {
        System.out.println("AfterEach");
    }
    @AfterAll
    static void destroy() {
        System.out.println("AfterAll");
    }

}