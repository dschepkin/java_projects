package com.dschepkin.javaCore.collection.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Map - нет дублирования ключей
 *
 * In HashMap - размещение элементов будет рандомным
 * In TreeMap - размещение в отсортированном виде
 *      Используется rad-black tree как структура хранения
 *      Сложность поиска и вставки O(log n)
 *      При каждой вставке проверяется необходимость перестройки дерева, если надо, то выполняется перестройка
 *      Таким образом, структура всегда - сбалансированное дерево
 */

public class TreeMapExample {
    public static void main(String[] args) {
        Person dima = new Person(45, "Dima", "Schepkin");
        Person vova = new Person(29, "Vladimir", "Dubov");
        Person roma = new Person(3, "Roman", "Sanin");
        Person vlad = new Person(10, "Vlad", "Bunin");
        Person lena = new Person(1, "Elena", "Lenina");

        Map<Integer, Person> person = new TreeMap<>();
        person.put(dima.getId(), dima);
        person.put(vova.getId(), vova);
        person.putIfAbsent(roma.getId(), roma);
        person.putIfAbsent(vlad.getId(), vlad);
        person.put(lena.getId(), lena);

        for (Map.Entry<Integer, Person> personEntry : person.entrySet()) {
            System.out.println(personEntry.getKey() + " " + personEntry.getValue().getName());

        }
    }
}
