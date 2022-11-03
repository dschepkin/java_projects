package com.dschepkin.library.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void getBookBeId() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(UserBook.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(Role_priv.class);
        configuration.addAnnotatedClass(Privilege.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            Book book = session.get(Book.class, 1L);
            System.out.println(book);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    //Before Spring configuration
}