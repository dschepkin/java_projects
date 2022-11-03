package com.dschepkin.javaCore.collection.map;

import com.dschepkin.javaCore.collection.map.Person;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * HashMap - отсортированная коллекция
 * В каком порядке были добавлены элементы, в таком храняться и в таком были выведены
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        Person dima = new Person(1, "Dima", "Schepkin");
        Person vova = new Person(2, "Vladimir", "Dubov");
        Person roma = new Person(3, "Roman", "Sanin");

        Map<Integer, Person> person = new LinkedHashMap<>();
        person.put(dima.getId(), dima);
        person.put(vova.getId(), vova);
        person.put(roma.getId(), roma);

        for (Map.Entry<Integer, Person> personEntry : person.entrySet()) {
            System.out.println(personEntry.getKey() + " " + personEntry.getValue().getName());
        }
    }
}
