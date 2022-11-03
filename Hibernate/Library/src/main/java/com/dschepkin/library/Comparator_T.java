package com.dschepkin.library;

import java.util.*;

public class Comparator_T {
    public static void main(String[] args) {
        //в дин массивах уже реализован Comparable, а также есть готовые Comparator
        Integer[] array = new Integer[]{5, 3, 4, 1, 2};
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, array);

        Collections.sort(list);
        Collections.sort(list, Comparator.reverseOrder());
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }

        //сортировка объектов
        var p1 = new Person("Vova", 36);
        var p2 = new Person("Diana", 8);
        var p3 = new Person("Anya", 20);
        var p4 = new Person("Dima", 40);
        var p5 = new Person("Dima", 15);
        var p6 = new Person("Dima", 8);

        List<Person> personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        personList.add(p6);

//        Collections.sort(personList); //№1
//        Collections.sort(personList, new ComparatorPersonAge()); //№2

        //через анонимный класс с сортировкой сначала по имена, а затем по возрасту
//        Collections.sort(personList,new Comparator<Person>() {
//            @Override
//            public int compare(Person p1, Person p2) {
//                int res = p1.getName().compareTo(p2.getName());
//                if(res == 0) {
//                    return p1.getAge() - p2.getAge();
//                }
//                return res;
//            }
//        });

        //через lambda. По имени, затем по возрасту по убыванию
        Collections.sort(personList,(o1,o2) -> {
            int res = o1.getName().compareTo(o2.getName());
            if(res == 0) {
                return o2.getAge() - o1.getAge();
            }
            return res;
        });

        for (Person person : personList) {
            System.out.println(person);
        }
    }
}

//№2 отдельный класс для сравнения по age
class ComparatorPersonAge implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }

}
//№1 Здесь предполагаем дефолтную естесствунную сортировку по имени
class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.getName());
    }
}
