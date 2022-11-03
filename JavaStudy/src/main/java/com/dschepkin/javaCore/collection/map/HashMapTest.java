package com.dschepkin.javaCore.collection.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Person dima = new Person(1, "Dima", "Schepkin");
        Person vova = new Person(2, "Vladimir", "Putin");

        Map<Integer, Person> person = new HashMap<>();
        person.put(dima.getId(),dima);
        person.put(vova.getId(), vova);

        /**
         * Списки:
         *  ключей
         *  значений
         *  node - т.е сущностей содержащих и ключ и значение
         *
         *  По сути в Map содержится 3 коллекции
         *      key
         *      value
         *      entry
         */
        System.out.println(person.keySet());
        System.out.println(person.values());
        System.out.println(person.entrySet());

        /**
         * итерируемся
         */
        System.out.println("Iterator");

        for (Integer key : person.keySet()) {
            System.out.println(key);
        }

        for (Person value : person.values()) {
            System.out.println(value.getId() + " " + value.getName());
        }

        for (Map.Entry<Integer, Person> personEntry : person.entrySet()) {
            System.out.println(personEntry.getKey() + " " + personEntry.getValue());
        }

        /**
         * другие удобные методы
         */
        person.containsKey(1); // O(1)
        person.containsValue(dima); //редко используется, т.к очень тяжело O(n)
        person.isEmpty();
        person.size();

        /**
         * put          - если добавляем по тому же ключу, то затрём текущее значение
         * putIfAbsend  - не перетирает
         */
        Person misha = new Person(1, "Misha", "Stasov");
        person.put(1,misha);
        person.putIfAbsent(1,misha);

        //если нет такого id, то вернёт misha, иначе сущствующее value
        person.getOrDefault(4, misha);
    }
}
