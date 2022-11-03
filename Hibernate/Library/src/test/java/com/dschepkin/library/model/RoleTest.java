package com.dschepkin.library.model;

import com.dschepkin.library.dao.RoleDAO;
import com.dschepkin.library.dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    void getRoleById() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Role role = session.get(Role.class, 2L);
            System.out.println(role);

            for(User user : role.getUsers()) {
                System.out.println(user);
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    //Before Spring configuration

    @Test
    void getAllRolesTest() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        RoleDAO roleDAO = annotationConfigApplicationContext.getBean(RoleDAO.class);
        roleDAO.getAllRoles().stream().forEach(System.out::println);
    }

    @Test
    void getRoleByIdTest() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        RoleDAO roleDAO = annotationConfigApplicationContext.getBean(RoleDAO.class);
        var role = roleDAO.getRoleById(1L);
        System.out.println(role);
    }

}