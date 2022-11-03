package com.dschepkin.javaCore.collection.map;

import java.util.Objects;

public class Person implements Comparable<Person>{
    private Integer id;
    private String name;
    private String surname;

    public Person(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    //будем сравнивать объекты только по id
    @Override
    public int compareTo(Person o) {
        //все три варианта ниже идентичны

        return Integer.compare(id,o.id);
/*
        return id - o.id; //может быть ошибка, если o.id будет очень большим отрицательным числом и выйдем за границы int

        if(id == o.id) {
            return 0;
        } else if(id > o.id) {
            return 1;
        } else {
            return -1;
        }
*/
    }
}
