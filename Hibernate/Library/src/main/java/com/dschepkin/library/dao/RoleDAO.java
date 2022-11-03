package com.dschepkin.library.dao;

import com.dschepkin.library.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RoleDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("SELECT * FROM roles",Role.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("SELECT * FROM roles WHERE id = :id", Role.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Transactional
    public void addRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
    }

    @Transactional
    public void updateRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
    }
}
