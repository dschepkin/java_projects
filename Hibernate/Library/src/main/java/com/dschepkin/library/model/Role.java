package com.dschepkin.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    //такой колонки нет в таблице
    //отсюда можно получить роли и всех пользователей, имеющих эту/эти роли
    @OneToMany(mappedBy = "roleId")
//    @OneToMany(mappedBy = "role")
    private List<User> users;

    @OneToMany(mappedBy = "role") //здесь название поля класса, а не поля таблицы
    private List<Role_priv> rolePrivs;

    public Role() {}

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public List<Role_priv> getRolePrivs() {
        return rolePrivs;
    }
}
