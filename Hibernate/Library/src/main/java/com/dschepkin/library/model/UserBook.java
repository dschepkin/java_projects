package com.dschepkin.library.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user_books")
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) //FK (owning site)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @OneToOne //FK (owning site)
    @JoinColumn(name="book_id",referencedColumnName = "id")
    private Book book;

    @Column(name = "take_date")
    private Date takeDate;

    @Column(name="put_date")
    private Date putDate;

    public UserBook() {
    }

    public UserBook(User user, Book book, Date takeDate) {
        this.user = user;
        this.book = book;
        this.takeDate = takeDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Date getPutDate() {
        return putDate;
    }

    public void setPutDate(Date putDate) {
        this.putDate = putDate;
    }
}
