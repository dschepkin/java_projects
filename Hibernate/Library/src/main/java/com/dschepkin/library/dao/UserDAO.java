package com.dschepkin.library.dao;

import com.dschepkin.library.model.Book;
import com.dschepkin.library.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDAO {
    //внедряем sessionFactory
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Transactional
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User updatedUser = session.get(User.class, user.getId());
        updatedUser.setSurname(user.getSurname());
    }

    @Transactional(readOnly = true)
    public List<User> getUserAndRoleByUserNameAndRoleName(String userName, String roleName) {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT u FROM User u JOIN FETCH Role r ON u.roleId = r.id WHERE u.name = :user_name and r.name = :role_name", User.class)
                .setParameter("user_name", userName)
                .setParameter("role_name", roleName)
                .getResultList();
    }
    //showAllUserBooks

    @Transactional(readOnly = true)
    public User showCurrentOwnerOfBook(String bookName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select u from User u join fetch u.Books ub join fetch ub.book b where b.name = :bookName", User.class)
                .setParameter("bookName", bookName)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    public User showUserRolesWithPrivsByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT u FROM User u JOIN FETCH u.roleId r JOIN FETCH r.rolePrivs rp JOIN FETCH rp.privilege p WHERE u.id = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
