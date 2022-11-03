package com.dschepkin.javaCore.reflection;

import java.util.Arrays;

public class ReflectionDemo {
    public static void main(String[] args) {
        Person dima = new Person(20, "Dima");
        String className = dima.getClass().getName();
        System.out.println(className);


    }

    static class Person {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
