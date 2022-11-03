package com.dschepkin.javaCore.collection.compare;

import com.dschepkin.javaCore.collection.map.Person;

import java.util.*;

/**
 * Строки можно сравнивать без каких либо доп действий, потому что класс String реализовал Comparable<String>
 * утилитный класс Collections использует дефолтный компаратор для строк
 *      компаратор используется вторым параметров в методе sort
 *
 * Для объектов сортировка не будет работать по дефолту
 * Чтобы можно было сравнивать объекты необходимо:
 * 1 реализовать интерфейс Comparable (функциональный интерф-йс с одним методов compareTo)
 *      compareTo return int:
 *          0 - если равны;
 *          1 - если у объекта, на котором вызываем compareTo, значение >
 *          -1 - если значение <
 * 2 реализовать метод compareTo
 *
 *
 */
public class CompareCollection {
    public static void main(String[] args) {
        List<String> nameImmutable = List.of("Dima", "Alisa", "Boris");//НЕизменяемый список
        List<String> arrayList = new ArrayList<>();//изменяемый список
        List<String> names = Arrays.asList("Dima", "Alisa", "Boris"); //изменяемый список

        Collections.sort(names);
        System.out.println(names); //[Alisa, Boris, Dima] - sorted

        /**
         * Теперь сортировка объектов
         *
         * Добавим сортировку по умолчанию
         */

        Person dima = new Person(3, "Boris", "Schepkin");
        Person vova = new Person(2, "Vladimir", "Putin");
        Person roma = new Person(1, "Alisa", "Sviridov");

        List<Person> person = new ArrayList<>();
        person.add(dima);
        person.add(vova);
        person.add(roma);

        Collections.sort(person);
        System.out.println(person);

        /**
         * Предоставить возможность сортировки по name
         * Реализация интерфейса Comparable позволяет выполнять сортировку только в одном варианте, что неудобно
         *
         * Для дополнительной сортировки можно применить интерфейс Comparator (тоже функциональный), метод compare
         * Для этого создадим дополнительнный класс компаратор PersonNameComparator
         */

        Collections.sort(person, new PersonNameComparator());
        System.out.println(person);

        //у интерфейса List есть свой метод sort, поэтому можем заменить на
        person.sort(new PersonNameComparator());

        //с 1.8 можем сотировать при помощи компаратора,
        // т.е можем сортировать вообще без создания нашего компаратора
        person.sort(Comparator.comparing(Person::getName));
        person.sort(Comparator.comparing(Person::getName).thenComparing(Person::getId)); //двойная сортировка
    }

    private static class PersonNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
