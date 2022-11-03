package com.dschepkin.library.service;

import com.dschepkin.library.dao.UserDAO;
import com.dschepkin.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getUserAndRoleByUserId(String userName, String roleName) {
//        return userDAO.getUserAndRoleByUserId(userName,roleName);
        return null;
    }

    public List<User> showAllUserBooks(Long userId) {
//        return userDAO.showAllUserBooks(userId);
        return null;
    }
}
