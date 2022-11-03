package com.dschepkin.javaCore.optional;

import javax.swing.*;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional - это обертка над объектом.
 *      Содержит только один единственный объект (не может содержать stream)
 *      В этой коробке либо есть объект или его нет
 *      это nullSafe - если в коробке пусто, то дальше метод не вызывается. Например consumer - System.out.print
 *          т.е не надо делать проверки у ссылки на null перед тем как вызвать что-то у нее
 * flatMap - возвращает Optional
 */
public class OptionalExample {
    public static void main(String[] args) {
        Optional<Student> studentOptional = Stream.of(
          new Student(18,"Dima"),
          new Student(39,"Vasya"),
          new Student(25,"Denis"),
          new Student(21,"Den"),
          new Student(42,"Sasha")
        )
                .sequential() //выполняем в один поток
                //находим самого старшего студента
                .filter(student -> student.getAge() < 22)
                .reduce(((student, student2) -> student.getAge() > student2.age ? student : student2));
        //если в Optional не будет объекта, т.к будет null, то метод System.out::println не будет вызван
        studentOptional.ifPresent(System.out::println);

    }

    static class Student {
        int age;
        String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
