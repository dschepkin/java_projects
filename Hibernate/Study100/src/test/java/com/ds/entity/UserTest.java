package com.ds.entity;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void getHqlUsers() {
        Configuration configuration = new Configuration();
        configuration.configure();

        /**
         * Вместо try-catch resources можно использовать анотацию @Cleanup
         */
        @Cleanup SessionFactory sessionFactory = configuration.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        String userName = "Lesya";
        String companyName = "Nokia";

        List<User> list = session.createQuery(
                "select u " +
                        "from User u " +
                        "join u.company c " +
                "where u.firstname = :firstName " +
                        "and c.name = :companyName"
                , User.class)
                .setParameter("firstName", userName)
                .setParameter("companyName", companyName)
                .list();

        System.out.println("");


        session.getTransaction().commit();
    }

    @Test
    void getCustomUser() {
        Configuration configuration = new Configuration();
        configuration.configure();

        /**
         * Вместо try-catch resources можно использовать анотацию @Cleanup
         */
        @Cleanup SessionFactory sessionFactory = configuration.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        String firstname = "Dima";

        List<User> user = session.createQuery(
                "select u from User u where u.firstname = :firstname", User.class
        )
                .setParameter("firstname", firstname)
                .list();

        System.out.println(user);

        session.getTransaction().commit();
    }

    @Test
    void getUser() {
        Configuration configuration = new Configuration();
        configuration.configure();

        /**
         * Вместо try-catch resources можно использовать анотацию @Cleanup
         */
        @Cleanup SessionFactory sessionFactory = configuration.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        User user = session.get(User.class, 1L);
        System.out.println(user);

        session.getTransaction().commit();
    }

    @Test
    void getOneToManyUsers() {
        Configuration configuration = new Configuration();
        configuration.configure();

        /**
         * Вместо try-catch resources можно использовать анотацию @Cleanup
         */
        @Cleanup SessionFactory sessionFactory = configuration.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        Company company = session.get(Company.class, 1L);
        System.out.println("");

        session.getTransaction().commit();
    }

    @Test
    void addUser() {
        Configuration configuration = new Configuration();
        configuration.configure();

        /**
         * Вместо try-catch resources можно использовать анотацию @Cleanup
         */
        @Cleanup SessionFactory sessionFactory = configuration.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();

        session.beginTransaction();

        Company company = Company.builder()
//                .id(2L)
                .build();

        User user = User.builder()
                .username("vova@gmail.com")
                .firstname("Vladimir")
                .lastname("Schepkin")
                .birthDate(LocalDate.of(1986, 5, 22))
                .age(36)
                .company(company)
                .build();

        session.save(user);

        session.getTransaction().commit();
    }

    @Test
    void addCompany() {
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Company company = Company.builder()
                    .name("Interfax")
                    .build();

            session.save(company);

            session.getTransaction().commit();
        }

    }

    @Test
    void addUserAndCompany() {
        Configuration configuration = new Configuration();

        /**
         * Добавляем сущность, чтобы hibernate мог с ней работать
         * или можно добавить в xml config mapping
         */

//        configuration.addAnnotatedClass(User.class);

        /**
         * метод принимает путь до конфиг файла hibernate.cfg.xml,
         * но т.к он у нас находится в дефолтной директории,
         * его не обязательно указывать
         */
        configuration.configure();

        /**
         * Создает SessionFactory на основании дефолтной конфигурации из класса
         * 1 configuration
         * 2 и метода configure(), который читает наш файл конфигурации
         *
         * SessionFactory надо закрывать и он существует один на все приложение
         */
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Company company = Company.builder()
                    .name("Nokia")
                    .build();

            User user = User.builder()
                    .username("dima@gmail.com")
                    .firstname("Dima")
                    .lastname("Schepkin")
                    .birthDate(LocalDate.of(1982, 2, 5))
                    .age(40)
                    .company(company)
                    .build();
            session.save(company);
            session.save(user);

            session.getTransaction().commit();
        }
    }
}