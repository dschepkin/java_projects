package com.dschepkin.library.dao;

import com.dschepkin.library.model.Role;
import com.dschepkin.library.model.Role_priv;
import com.dschepkin.library.model.User;
import com.dschepkin.library.model.UserBook;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
//    private static AnnotationConfigApplicationContext annotationConfigApplicationContext;
//
//    @BeforeTestClass
//    public static void setAnnotationConfigApplicationContextInit() {
//        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
//    }

    @Test
    void getUserById() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        UserDAO bean = annotationConfigApplicationContext.getBean(UserDAO.class);

        System.out.println(bean.getUserById(3L));
    }

    @Test
    void getAllUsersTest() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        UserDAO userDAO = annotationConfigApplicationContext.getBean(UserDAO.class);
        List<User> allUsers = userDAO.getAllUsers();
//        allUsers.stream().forEach(u -> System.out.println(u));
        allUsers.stream().forEach(System.out::println);
    }

    @Test
    void addUserTest() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        UserDAO userDAO = annotationConfigApplicationContext.getBean(UserDAO.class);
        User user = new User("Julia", "Muhina", new Role(1L));
        userDAO.addUser(user);
        System.out.println();

    }

    @Test
    void updateUserTest() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        UserDAO userDAO = annotationConfigApplicationContext.getBean(UserDAO.class);
        User user = userDAO.getUserById(8L); //получаем пользователя в контекст и теперь просто изменяем объект
        user.setSurname("Novikova"); //т.е объект в managed состоянии, то синхронизируется с БД после завершения сессии
        userDAO.updateUser(user);
    }

    @Test
    void getUserAndRoleByUserNameAndRoleName() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        UserDAO userDAO = annotationConfigApplicationContext.getBean(UserDAO.class);

        List<User> users = userDAO.getUserAndRoleByUserNameAndRoleName("Max", "READER_TECH");
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void showCurrentOwnerOfBook() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        UserDAO userDAO = annotationConfigApplicationContext.getBean(UserDAO.class);
        User user = userDAO.showCurrentOwnerOfBook("Мастер и маргарита");
        System.out.println(user);
        //получим имя книги из user (просто для теста)
        List<UserBook> books = user.getBooks();
        for(UserBook userBooks : books) {
            System.out.println(userBooks.getBook().getName());
        }
    }
    /*
    User{id=3, name='Max', surname='Maximus', roleId=Role{id=2, name='READER_TECH', description='null'}}
    Мастер и маргарита
     */

    @Test
    void showUserRolesWithPrivs() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(com.dschepkin.library.config.SpringConfig.class);
        UserDAO userDAO = annotationConfigApplicationContext.getBean(UserDAO.class);
        User user = userDAO.showUserRolesWithPrivsByUserId(3L);
        Role roleId = user.getRoleId();
        System.out.println(user);
        System.out.println("RoleId = "+roleId);
        List<Role_priv> rolePrivs = roleId.getRolePrivs();
        for(Role_priv rolePriv : rolePrivs) {
            System.out.println("Privilage name = " +rolePriv.getPrivilege().getName());
        }
    }

    /*
    User{id=3, name='Max', surname='Maximus', roleId=Role{id=2, name='READER_TECH', description='null'}}
    RoleId = Role{id=2, name='READER_TECH', description='null'}
    Privilage name = TECH
    Privilage name = MED
     */
}