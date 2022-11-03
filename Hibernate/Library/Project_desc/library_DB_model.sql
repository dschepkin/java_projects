Create new connection
username/password: postgres/postgres
port: 5432
database: library
jdbc:postgresql://localhost:5432/library

CREATE DATABASE library;
CREATE SCHEMA spring_study

CREATE TABLE roles (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(25) NOT NULL UNIQUE,
    description VARCHAR(256)
);

COMMENT ON TABLE privileges IS 'Роли предоставляемые пользователям бибилотеки';

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('READER_TECH');
INSERT INTO roles (name) VALUES ('READER_MED');
INSERT INTO roles (name) VALUES ('READER_ART');

CREATE TABLE privileges (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(25) NOT NULL UNIQUE,
    desctiption VARCHAR(256)
)

COMMENT ON TABLE privileges IS 'разрешения на чтением разных тематик: художественная, техническая, медицина...';

INSERT INTO privileges (name) VALUES ('ALL');
INSERT INTO privileges (name) VALUES ('TECH');
INSERT INTO privileges (name) VALUES ('MED');
INSERT INTO privileges (name) VALUES ('ART');

CREATE TABLE role_privs (
    id BIGSERIAL PRIMARY KEY,
    role_id BIGINT REFERENCES roles(id) NOT NULL,
    priv_id BIGINT REFERENCES privileges(id) NOT NULL,
    UNIQUE (role_id,priv_id)
)

INSERT INTO role_privs (role_id, priv_id) VALUES (1,1);
--Роль TECH имеет доступ к разделам TECH and MED
INSERT INTO role_privs (role_id, priv_id) VALUES (2,2);
INSERT INTO role_privs (role_id, priv_id) VALUES (2,3);

INSERT INTO role_privs (role_id, priv_id) VALUES (3,3);
INSERT INTO role_privs (role_id, priv_id) VALUES (4,4);

select
    r.name role,
    p.name priv
from role_privs rp
join roles r on r.id = rp.role_id
join privileges p on rp.priv_id = p.id
order by role

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    role_id BIGINT REFERENCES roles(id) NOT NULL,
    account_status VARCHAR,
    password VARCHAR,
    created TIMESTAMP,
    UNIQUE (name,surname),
    CHECK ( account_status IN ('OPEN','LOCKED') )
)

CREATE TABLE books (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR NOT NULL,
                       autor VARCHAR NOT NULL,
                       add_date TIMESTAMP,
                       expire_date TIMESTAMP,
                       UNIQUE (name, autor)
)

CREATE TABLE user_books (
                            id BIGSERIAL PRIMARY KEY ,
                            user_id BIGINT REFERENCES users(id) NOT NULL ,
                            book_id BIGINT REFERENCES books(id) NOT NULL ,
                            take_date TIMESTAMP NOT NULL ,
                            put_date TIMESTAMP NOT NULL
)

INSERT INTO books (name,autor) VALUES ('Мастер и маргарита','Булгаков')
INSERT INTO books (name,autor) VALUES ('Война и мир','Толстой')

INSERT INTO user_books (user_id,book_id,take_date,put_date) VALUES (3,1,current_timestamp,current_timestamp)
INSERT INTO user_books (user_id,book_id,take_date,put_date) VALUES (3,2,current_timestamp,current_timestamp)

CREATE TABLE limits (
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR NOT NULL UNIQUE ,
    value INT NOT NULL,
    CHECK ( name IN ('max_rent_period','max_count_book_rent','max_count_book_rent_after_violation') )
)
