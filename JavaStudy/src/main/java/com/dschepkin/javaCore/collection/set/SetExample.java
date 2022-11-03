package com.dschepkin.javaCore.collection.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * - Set - это реализация Map
 * - нет дублей
 * - нет метода sort, как у List
 * - если нужна сортировка, то надо использовать TreeSet
 * - не можем как у List получить значение по индексу.
 *      Надо использовать forEach (функцональный интер-с) или обычный foreach
 *
 * HashSet          - порядок не сохраняется (это реализация Map: хранение в ключах, а value всегда Dummy = new Object())
 * LinkedHashSet    - порядок сохраняется
 * TreeSet          - отсортированный (Сортирует по дефолту или принимает в конструктор Comparator)
 *
 */
public class SetExample {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("bbb");
        hashSet.add("ccc");
        hashSet.add("aaa");
        //метод add возвращает boolean
        System.out.println("Is emenent add to SET - " + hashSet.add("aaa"));

        System.out.println("HashSet " + hashSet);

        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("ccc");
        linkedHashSet.add("bbb");
        linkedHashSet.add("ccc");
        linkedHashSet.add("aaa");

        System.out.println("LinedHashSet " + linkedHashSet);

        Set<String> treeSet = new TreeSet<>();
        treeSet.add("ccc");
        treeSet.add("bbb");
        treeSet.add("ccc");
        treeSet.add("aaa");

        System.out.println("TreeSet " + treeSet);
    }
}
