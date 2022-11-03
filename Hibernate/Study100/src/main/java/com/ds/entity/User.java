package com.ds.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
  * Этот класс является сущностью, потому что он
 * 1 POJO
 * 2 Содержит аннотации @Entity and @Id
  */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "company")//чтобы при LAZY не подтягивал Company
@Builder

@Entity
@Table(name = "users", schema = "public")
public class User implements BaseEntity<Long> {
    /**
     * Определяем это поле как PK
     * Говорим что его не надо декларировать на ур-не сущности,
     *  а он будет автогенерироваться БД, т.к тип атрибута BIGSERIAL
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * это на БД на отражается;
     * полезно для информирования разработчиков
     * , а также при автогенерации DDL
     */
    @Column(unique = true)
    private String username;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private Integer age;
    /**
     * @ManyToOne(optional = false)
     * optional = false говорит, что company_id у Users не может быть null
     * т.е при select будет выполняться inner join, а не left join
     */
    @ManyToOne(fetch = FetchType.LAZY) //2 запроса в БД. Пока не запрашиваем Company, не запрашиваем
    @JoinColumn(name = "company_id")
    private Company company;
}
