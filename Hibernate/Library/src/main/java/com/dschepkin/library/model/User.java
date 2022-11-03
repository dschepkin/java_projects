package com.dschepkin.library.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER) //FK (owning site)
    //у одного пользователя может быть только 1 роль, т.е Много пользователей могут иметь одну роль @ManyToOne
    //указываем имя колонки в таблице user, которое ссылается на roles
    //это можно и не указывать, т.к по дефолту будет использоваться имя типа переменной, т.е Role + PK = id -> role_id
    @JoinColumn(name="role_id", referencedColumnName = "id")
    private Role roleId;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //на эту таблицу ссылаются. Здесь имя поля класса в UserBook, а не поля в таблице
    //Один пользователь может иметь много книг @OneToMany
    private List<UserBook> Books;

    @Column(name="account_status")
    private String accountStatus;

    @Column(name="password")
    private String password;

    @Column(name="created")
    private Timestamp created;

    public User() {}

    public User(String name, String surname, Role roleId) {
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
    }

    public User(String name, String surname, Role role_id, String accountStatus, String password, Timestamp created) {
        this.name = name;
        this.surname = surname;
        this.roleId = role_id;
        this.accountStatus = accountStatus;
        this.password = password;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setRoleId(Role role) {
    }

    public Role getRoleId() {
        return roleId;
    }

    public List<UserBook> getBooks() {
        return Books;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
