package com.dschepkin.library.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void getUserById() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(Role_priv.class);
        configuration.addAnnotatedClass(Privilege.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(UserBook.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            User user = session.get(User.class, 3L);
            System.out.println();
            System.out.println(user);

            List<UserBook> books = user.getBooks();
            for(UserBook userBook : books) {
                System.out.println(userBook.getBook());
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    @Test
    void saveUser() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            User user1 = new User();
            user1.setName("Max");
            user1.setSurname("Maximus");
            user1.setRoleId(new Role(2L));

            User user2 = new User("Vladik", "Dumin", new Role(3L));

            session.save(user1);
            session.save(user2);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    @Test
    void updateUser() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            User user = session.get(User.class, 4);
            user.setSurname("Maximov");

            session.getTransaction().commit();

            //после commit в объект помещается sequence id
            System.out.println(user.getId() + " " + user);
        } finally {
            sessionFactory.close();
        }
    }

    @Test
    void deleteUser() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            User user = session.get(User.class, 4L);
            session.delete(user);

            session.getTransaction().commit();

            //после commit в объект помещается sequence id
            //т.е даже после удаления из БД сущность есть в контексте hibernate
            System.out.println(user.getId() + " " + user);
        } finally {
            sessionFactory.close();
        }
    }

    //HQL
    @Test
    void hqlGetAllUsers() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            List<User> userList = session.createQuery("FROM User",User.class)
                    .getResultList();

            for(User user : userList) {
                System.out.println(user);
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    @Test
    void hqlGetUserById() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            User user = session.createQuery(
                    "SELECT u " +
                            "FROM User u " +
                            "where u.id=:id " +
                            "and u.name=:name"
                    ,User.class)
                    .setParameter("id",3L)
                    .setParameter("name","Max")
                    .uniqueResult();

            System.out.println(user);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    @Test
    void hqlUpdateUser() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            session.createQuery("UPDATE User set surname='Bulkin' where id = 2")
                    .executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    @Test
    void hqlDeleteUser() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            session.createQuery("DELETE User where id = 2")
                    .executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    //Before Spring configuration
}