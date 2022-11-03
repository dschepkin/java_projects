package com.dschepkin.javaCore.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();//изменяемый список
        List<String> nameImmutable = List.of("Dima", "Alisa", "Boris");// НЕизменяемый список
        /**
         * Изменяемый список (но размер неизменный после создания):
         *      можно изменять значения
         *      нельзя удалять
         *      нельзя добавлять
         */
        List<String> names = Arrays.asList("Dima", "Alisa", "Boris");

        Collections.sort(names);
        System.out.println(names); //[Alisa, Boris, Dima] - sorted
    }
}
