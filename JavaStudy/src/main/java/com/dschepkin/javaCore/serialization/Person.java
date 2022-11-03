package com.dschepkin.javaCore.serialization;

import java.io.Serializable;

public class Person implements Serializable {
    public static final long serialVersionUID = 1L;

    private int age;
    private String name;
    private transient String address;

    public Person(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
