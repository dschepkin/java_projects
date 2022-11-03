package com.ds.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * Если используется Set, а не List, то надо исключить поле from
 * ToString and Equals and HashCode
 * Иначе будет зацикливание
 *
 * Если получаем коллекцию List, то исключать не обязательно
 */

@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")

@Builder

@Entity
@Table(name = "company", schema = "public")
public class Company implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "company")
    /**
     * Если связи нет, то можно использовать
     * @JoinColumn(name = "company_id")
     */

//    private List<User> user;
    private Set<User> users;
}
