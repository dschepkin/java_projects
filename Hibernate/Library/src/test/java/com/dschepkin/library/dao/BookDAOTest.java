package com.dschepkin.library.dao;

import com.dschepkin.library.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {
    @Test
    void getBookById() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        BookDAO bookDAO = annotationConfigApplicationContext.getBean(BookDAO.class);
        System.out.println(bookDAO.getBookById(1L));
    }

    @Test
    void getAllBook() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        BookDAO bookDAO = annotationConfigApplicationContext.getBean(BookDAO.class);

        List<Book> listBook = bookDAO.getAllBooks();
        for(Book book : listBook) {
            System.out.println(book);
        }
    }


    @Test
    void showAllUserBooks() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        BookDAO bookDAO = annotationConfigApplicationContext.getBean(BookDAO.class);
        System.out.println(bookDAO.showAllUserBooks(3L));
    }

}