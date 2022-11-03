package com.dschepkin.library.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="autor")
    private String autor;

    @Column(name="add_date")
    private Date addDate;

    @Column(name="expire_date")
    private Date expireDate;

    @OneToOne(mappedBy = "book",fetch = FetchType.LAZY) //только одна книга может быть выдана
    private UserBook userBook;

    public Book() {
    }

    public Book(String name, String autor, Date addDate) {
        this.name = name;
        this.autor = autor;
        this.addDate = addDate;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public UserBook getUserBook() {
        return userBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
