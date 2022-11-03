package com.dschepkin.library.dao;

import com.dschepkin.library.model.Book;
import com.dschepkin.library.model.UserBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//addBook
//updateBook
//deleteBook

//    @Transactional
//    public List<Book> getBooksByUserId(Long id) {
//        var session = sessionFactory.getCurrentSession();
//        var cb = session.getCriteriaBuilder();
//
//        var criteriaQuery = cb.createQuery(Book.class);
//        var book = criteriaQuery.from(Book.class);
//        var userBooks = book.join(book.get("userBook"));
//        var user = userBooks.join(userBooks.get("user"));
//    }

    //getBookById
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        var session = sessionFactory.getCurrentSession();
        var cb = session.getCriteriaBuilder();

        var criteriaQuery = cb.createQuery(Book.class);
        var book = criteriaQuery.from(Book.class);

        criteriaQuery.select(book).where(
                cb.equal(book.get("id"),id)
        );

        return session.createQuery(criteriaQuery)
                .uniqueResult();
    }

    //getAllBook
    @Transactional
    public List<Book> getAllBooks() {
        var session = sessionFactory.getCurrentSession();
        var cb = session.getCriteriaBuilder();

        var criteriaQuery = cb.createQuery(Book.class);
        var books = criteriaQuery.from(Book.class);

        criteriaQuery.select(books);

        return session.createQuery(criteriaQuery)
                .list();
    }


    @Transactional
    public List<Book> showAllUserBooks(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT b FROM Book b JOIN b.userBook ub JOIN ub.user u WHERE u.id = :id", Book.class)
                .setParameter("id", userId)
                .getResultList();
    }
}
